$(document).ready(function () {
    findAll();
});

function closeModalEdit() {
    $('#myModal').hide();
}

function strAddUser(user) {
    return "<tr id='userList" + user.id + "'>" +
        "<td class='tdID'>" + user.id + "</td>" +
        "<td class='tdRoles'>" + roleToStr(user.roles) + "</td>" +
        "<td class='tdUsername'>" + user.username + "</td>" +
        "<td class='tdPassword'>" + user.password + "</td>" +
        "<td class='tdEmail'>" + user.email + "</td>" +
        "<td><button id='" + user.id + "' onclick='showModalEdit(this.id);' class='btn btn-info' type='button' >Edit</button></td>" + <!-- data-toggle='modal' data-target='#myModal" + user.id + "' onclick='showModalEdit(this.id);' -->
        "<td><button id='" + user.id + "' onclick='deleteUser(this.id);' class='btn btn-danger' type='button'>Delete</button></td></tr>";
}

function addUserPage(element, position, user) {
    element.insertAdjacentHTML(position, strAddUser(user));
}

function getAllUsers(element, position, userList) {
        let userListHtml = document.getElementById(element);
        for (let i = userList.length - 1; i >= 0; i--) {
            addUserPage(userListHtml, position, userList[i]);
        }
}

function addUser() {

    let user = {
        email: $("#userEmail").val(),
        username: $("#userName").val(),
        password: $("#userPassword").val(),
        roles: $("#userRoles").val()
    };

    $.ajax({
        url: '/rest/create',
        contentType: 'application/json;',
        // mimeType: 'application/json',
        data: JSON.stringify(user),
        type: 'POST',
        dataType: 'JSON',
        success: function (user) {
            $('#myTab a[href="#users-table"]').tab('show');
            let userListHtml = document.getElementById('userListHtml');
            addUserPage(userListHtml, 'beforeend', user);
        }
    });
}

function findAll() {
    $.ajax({
        url: '/rest/list',
        contentType: 'application/json; charset=UTF-8;',
        mimeType: 'application/json',
        type: 'GET',
        dataType: 'JSON',
        success: function (userList) {
            getAllUsers('userListHtml', 'afterBegin', userList);
        }
    });
}

function editUser(id) {
    const roles = [];

    let selectRoles = document.getElementById('userRolesList').options;

    for (let i = 0; i < selectRoles.length; i++) {
        if (selectRoles[i].selected) {
            roles.push(JSON.parse('{"id":"' + selectRoles[i].id + '", "name":"' + selectRoles[i].value + '"}'));
        }
    }

    let user = {
        id: $('#userIdList').val(),
        email: $('#userEmailList').val(),
        username: $('#userNameList').val(),
        password: $('#userPasswordList').val(),
        roles: roles,
    };

    $.ajax({
        url: '/rest/update',
        contentType: 'application/json;',
        mimeType: 'application/json',
        data: JSON.stringify(user),
        type: 'PUT',
        dataType: 'JSON',
        success: function () {
            $('#userList' + user.id).replaceWith(strAddUser(user));
            $('#myModal').hide();
        }
    });
}

function deleteUser(id) {

    $.ajax({
        url: '/rest/delete',
        contentType: 'application/json; charset=UTF-8;',
        mimeType: 'application/json',
        type: 'DELETE',
        data: JSON.stringify(id),
        dataType: 'JSON',
        success: function () {
            $(`#userList${id}`).hide();
        }
    });
}

function showModalEdit(id) {

    $('#userIdList').attr('value',
        id);
    $('#userEmailList').attr('value',
        $(`#userList${id} td.tdEmail`).text());
    $('#userNameList').attr('value',
        $(`#userList${id} td.tdUsername`).text());
    $('#userPasswordList').attr('value',
        $(`#userList${id} td.tdPassword`).text());
    $('#userRolesList').attr('value',
        $(`#userList${id} td.tdRoles`).text());

    $('#myModal').show();
}

function roleToStr(roles) {
    let strRole = '';
    for (let i = 0; i < roles.length; i++) {
        strRole = strRole + roles[i].name + ' ';
    }
    return strRole.substring(0, strRole.length - 1);
}
