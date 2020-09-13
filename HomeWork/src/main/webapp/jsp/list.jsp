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
            form.id = data.id;

            let table = document.createElement("table");
            form.appendChild(table);

            let tbody = document.createElement("tbody");
            table.appendChild(tbody);

            let tr = document.createElement("tr");
            tbody.appendChild(tr);

            let id = document.createElement("input");
            id.name = "id";
            id.readOnly = true;
            id.placeholder = "编号";
            id.value = data.id;
            id.type = "text";
            let td = document.createElement("td");
            td.appendChild(id);
            tr.appendChild(td);

            let name = document.createElement("input");
            name.name = "name";
            name.placeholder = "姓名";
            name.value = data.name;
            name.type = "text";
            td = document.createElement("td");
            td.appendChild(name);
            tr.appendChild(td);

            let phone = document.createElement("input");
            phone.name = "phone";
            phone.placeholder = "电话";
            phone.value = data.phone;
            phone.type = "text";
            td = document.createElement("td");
            td.appendChild(phone);
            tr.appendChild(td);

            let address = document.createElement("input");
            address.name = "address";
            address.placeholder = "地址";
            address.value = data.address;
            address.type = "text";
            td = document.createElement("td");
            td.appendChild(address);
            tr.appendChild(td);

            td = document.createElement("td");
            td.innerHTML = "<button id = 'update_" + data.id + "' type='submit' onclick='return update();'>更新</button>";
            tr.appendChild(td);

            td = document.createElement("td");
            td.innerHTML = "<button id = 'delete_" + data.id + "' type='submit' onclick='return deleted();'>删除</button>";
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

        function update() {
            let event_id = event.target.id;
            let id = event_id.replace("update_", "");
            let form = document.getElementById(id);

            $.ajax({
                url: "/resume/save",
                async: true,
                data: "{\"id\":" + form.getElementsByName("id").value + ",\"name\":\"" + form.getElementsByName("name").value + "\",\"phone\":\"" + form.getElementsByName("phone").value + "\",\"address\":\"" + form.getElementsByName("address").value + "\"}",
                contentType: 'application/json;charset=utf-8',
                dataType: "json",
                type: "POST",
                success: function (result) {
                    alert(result);
                    form.getElementsByName("name").value = result.name;
                    form.getElementsByName("phone").value = result.phone;
                    form.getElementsByName("address").value = result.address;
                }
            })
            return false;
        }

        function deleted() {
            let event_id = event.target.id;
            let id = event_id.replace("delete_", "");
            $.ajax({
                url: "/resume/delete",
                async: true,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                data: "id=" + id,
                type: "POST",
                complete: function (xhr, status) {
                    let child = document.getElementById(id);
                    child.parentElement.removeChild(child);
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
<div id="thead">
    <form target="_self">
        <table>
            <thead>
            <tr>
                <th>编号</th>
                <th>姓名</th>
                <th>电话</th>
                <th>地址</th>
                <th colspan="2">操作</th>
            </tr>
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
            </thead>
        </table>
    </form>
</div>
<div id="tbody"></div>
</body>
</html>