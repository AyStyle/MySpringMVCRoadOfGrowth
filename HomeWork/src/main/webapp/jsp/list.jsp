<DOCTYPE html/>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script type="text/javascript">
        function load() {
            let ajax = new XMLHttpRequest();
            ajax.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    listElement(ajax.responseText);
                }
            };
            ajax.open("POST", "/resume/queryAll", true);
            ajax.send();
        }

        function listElement(response) {
            let tbody = document.getElementById("tbody");
            // 先清空内容，然后在添加内容
            tbody.innerHTML = "";

            let jsonArray = JSON.parse(response)
            for (let jsonIndex in jsonArray) {
                let form = createForm(jsonArray[jsonIndex]);

                tbody.appendChild(form);
            }
        }

        function createForm(data) {
            let form = document.createElement("form");
            form.target = "_self";
            form.method = "post";

            let id = document.createElement("input");
            id.readOnly = true;
            id.placeholder = "编号";
            id.type = "text";
            id.value = data.id;
            form.appendChild(id);

            let name = document.createElement("input");
            name.placeholder = "姓名";
            name.type = "text";
            name.value = data.name;
            form.appendChild(name);

            let phone = document.createElement("input");
            phone.placeholder = "电话";
            phone.type = "text";
            phone.value = data.phone;
            form.appendChild(phone);

            let address = document.createElement("input");
            address.placeholder = "地址";
            address.type = "text";
            address.value = data.address;
            form.appendChild(address);

            let update = document.createElement("button");
            update.type = "submit";
            update.value = "更新";
            update.onclick = this.update(form);
            form.appendChild(update);

            let deleted = document.createElement("button");
            deleted.type = "submit";
            deleted.value = "删除";
            deleted.onclick = this.deleted(form);
            form.appendChild(deleted);

            return form;
        }

        function create() {
            let ajax = new XMLHttpRequest();
            ajax.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    let form = createForm(JSON.parse(this.responseText));
                    document.getElementById("tbody").appendChild(form);
                    alert("添加成功！");
                }else{
                    alert("添加失败！");
                }
            };
            ajax.open("POST", "/resume/save", true);
            ajax.setRequestHeader("Content-type", "application/json")
            ajax.send("{name:" + document.getElementById("create_name") + ",phone:" + document.getElementById("create_phone") + ",address:" + document.getElementById("create_address") + "}");

            return false;
        }

        function update(form) {
            let ajax = new XMLHttpRequest();
            ajax.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    alert("更新成功！");
                } else {
                    alert("更新失败！");
                    query(form[0].value, form);
                }
            };
            ajax.open("POST", "/resume/save", true);
            ajax.setRequestHeader("Content-type", "application/json")
            ajax.send("{id:" + form[0].value + ",name:" + form[1].value + ",phone:" + form[2].value + ",address:" + form[3].value + "}");

            return false;
        }

        function deleted(form) {
            let ajax = new XMLHttpRequest();
            ajax.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    alert("删除成功！")
                    form.remove();
                }else{
                    alert("删除失败！");
                }
            };
            ajax.open("POST", "/resume/delete", true);
            ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
            ajax.send("id=" + form[0].value);

            return false;
        }

        function query(id, form) {
            let ajax = new XMLHttpRequest();
            ajax.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    let data = JSON.parse(this.responseText);
                    form[0].value = data.id;
                    form[1].value = data.name;
                    form[2].value = data.phone;
                    form[3].value = data.address;
                } else {
                    alert("没有查到对应的内容, Id：" + id);
                }
            };
            ajax.open("POST", "/resume/query", true);
            ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
            ajax.send("id=" + id);

            return false;
        }
    </script>
</head>

<body onload="load()">
<div>
    <table cellpadding="0" cellspacing="0" border="0" bgcolor="#dcdcdc">
        <thead>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>电话</th>
            <th>地址</th>
            <th colspan="2">操作</th>
        </tr>
        <form method="post" target="_self">
            <tr>
                <td><input placeholder="编号" type="text" disabled="disabled"/></td>
                <td><input id="create_name" placeholder="姓名" type="text" required="required"/></td>
                <td><input id="create_phone" placeholder="电话" type="text" required="required"/></td>
                <td><input id="create_address" placeholder="地址" type="text" required="required"/></td>
                <td>
                    <button type="submit" onclick=create()>添加</button>
                </td>
                <td>
                    <button type="reset">清空</button>
                </td>
                </td>
            </tr>
        </form>
        </thead>
        <tbody id="tbody">
        </tbody>
    </table>
</div>

</body>
</html>