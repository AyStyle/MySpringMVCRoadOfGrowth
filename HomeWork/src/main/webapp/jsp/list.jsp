<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script src="/js/jquery.min.js"></script>
    <script type="text/javascript">
        function load() {
            let xhr = new XMLHttpRequest();

            xhr.onload = function () {
                let tbody = document.getElementById("tbody");
                // 先清空内容，然后在添加内容
                tbody.innerHTML = "";
                try {
                    result = JSON.parse(this.responseText)

                    for (index in result) {
                        let form = createForm(result[index]);
                        tbody.appendChild(form);
                    }
                } catch (e) {
                    window.location = "/jsp/login.jsp"
                }

            }

            xhr.open("POST", "/resume/queryAll")
            xhr.send()
        }

        function createForm(data) {
            let form = document.createElement("form");
            form.target = "_self";
            form.method = "post";

            let tr = document.createElement("tr");
            form.appendChild(tr);

            let id = document.createElement("input");
            id.name = "id";
            id.readOnly = true;
            id.placeholder = data.id;
            id.type = "text";
            let td = document.createElement("td");
            td.appendChild(id);
            tr.appendChild(td);

            let name = document.createElement("input");
            name.name = "name";
            name.placeholder = data.name;
            name.type = "text";
            td = document.createElement("td");
            td.appendChild(name);
            tr.appendChild(td);

            let phone = document.createElement("input");
            phone.name = "phone";
            phone.placeholder = data.phone;
            phone.type = "text";
            td = document.createElement("td");
            td.appendChild(phone);
            tr.appendChild(td);

            let address = document.createElement("input");
            address.name = "address";
            address.placeholder = data.address;
            address.type = "text";
            td = document.createElement("td");
            td.appendChild(address);
            tr.appendChild(td);

            let update = document.createElement("button");
            update.type = "submit";
            update.innerText = "更新";
            update.onclick = "return this.update();";
            td = document.createElement("td");
            td.appendChild(update);
            tr.appendChild(td);

            let deleted = document.createElement("button");
            deleted.type = "submit";
            deleted.innerText = "删除";
            deleted.onclick =  "return this.deleted();";
            td = document.createElement("td");
            td.appendChild(deleted);
            tr.appendChild(td);

            return form;
        }

        function create() {
            $.ajax({
                url: "/resume/save",
                async: true,
                data: "{\"name\":\"" + document.getElementById("create_name").value + "\",\"phone\":\"" + document.getElementById("create_phone").value + "\",\"address\":\"" + document.getElementById("create_address").value + "\"}",
                contentType: 'application/json;charset=utf-8',
                dataType: "json",
                type: "POST",
                success: function (result) {
                    let form = createForm(result);
                    document.getElementById("tbody").appendChild(form);
                }
            })
            return false;
        }

        function update(form) {
            $.ajax({
                url: "/resume/save",
                async: true,
                data: "{\"id\":" + form.getElementsByName("id").value + ",\"name\":\"" + form.getElementsByName("name").value + "\",\"phone\":\"" + form.getElementsByName("phone").value + "\",\"address\":\"" + form.getElementsByName("address").value + "\"}",
                contentType: 'application/json;charset=utf-8',
                dataType: "json",
                type: "POST",
                success: function (result) {
                    form.getElementsByName("name").value = result.name;
                    form.getElementsByName("phone").value = result.phone;
                    form.getElementsByName("address").value = result.address;
                }
            })
            return false;
        }

        function deleted(form) {
            $.ajax({
                url: "/resume/save",
                async: true,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                data: "id=" + form.getElementsByName("id").value,
                dataType: "json",
                type: "POST",
                success: function (result) {
                    form.delete();
                }
            })
            return false;
        }

        function query(form) {
            $.ajax({
                url: "/resume/save",
                async: true,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                data: "id=" + form.getElementsByName("id").value,
                dataType: "json",
                type: "POST",
                success: function (result, status) {

                }
            })
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
        <form target="_self">
            <tr>
                <td><input placeholder="编号" type="text" disabled="disabled"/></td>
                <td><input id="create_name" placeholder="姓名" type="text" required="required"/></td>
                <td><input id="create_phone" placeholder="电话" type="text" required="required"/></td>
                <td><input id="create_address" placeholder="地址" type="text" required="required"/></td>
                <td>
                    <button type="submit" onclick="return create();">添加</button>
                </td>
                <td>
                    <button type="reset">清空</button>
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