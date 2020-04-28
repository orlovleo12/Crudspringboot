$(document).ready(function () {
    fire_ajax_submit()

});
$("#button-edit-user").on("click", function(){
    const roles = [];

    let selectRoles = document.getElementById('userRolesList').options;

    for (let i = 0; i < selectRoles.length; i++) {
        if (selectRoles[i].selected) {
            roles.push(JSON.parse('{"id":"' + selectRoles[i].id + '", "name":"' + selectRoles[i].value + '"}'));
        }
    }
    var formData = {
        "id": $("#inputid").attr("placeholder"),
        "lastName": $("#inputlname").attr("placeholder"),
        "firstName": $("#inputfname").attr("placeholder"),
        "age": $("#inputage").attr("placeholder"),
        "password": $("#inputpass").attr("placeholder"),
        "email": $("#inputemail").attr("placeholder"),
        roles: roles,
    };

    $.ajax({
        url: '/admin/edit',
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        type: 'PUT',
        dataType: 'JSON',
        success: function () {

        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
});


$("#feedback").on('click', '#button-edit', function () {

    $(".modal-header h4").text("User edit");
    var bookId = $(this).data('book-id');
    /*var data = $("#button-edit").attr('data-target');*/
    $("#inputid").attr("placeholder", bookId);
    $("#modalEdit").modal();
    get_user_by_id(bookId);
})

function get_user_by_id(id){
    $.ajax({
        url: '/admin/edit/'+id,
        contentType: 'application/json; charset=UTF-8;',
        mimeType: 'application/json',
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            $("#inputemail").attr("placeholder", data.email);
            $("#inputlname").attr("placeholder", data.lastName);
            $("#inputfname").attr("placeholder", data.firstName);
            $("#inputage").attr("placeholder", data.age);
            $("#inputpass").attr("placeholder", " ");
        }
    });

}

function fire_ajax_submit() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/list",
        dataType: 'json',

        success: function (data) {
            $.each(data, function (i, item) {

                var tr = $('<tr/>');
                tr.append("<td>" + item.id + "</td>");
                tr.append("<td>" + item.firstName + "</td>");
                tr.append("<td>" + item.lastName + "</td>");
                tr.append("<td>" + item.age + "</td>");
                tr.append("<td>" + item.email + "</td>");

                tr.append("<td> <button type=\"button\"\n" +
                    "class=\"btn btn-primary\"\n" +
                    "id=\"button-edit\"\n" +
                    "data-toggle=\"modal\"\n" +

                    "data-book-id=" + item.id + ">\n" +
                    "\n" +
                    "Edit</button></td>" + "<td> <button type=\"button\"\n" +
                    "class=\"btn btn-primary\"\n" +
                    "id=\"button-delete\"\n" +
                    "data-toggle=\"modal\"\n" +
                    " data-target=\"#modalDelete\"\n" +
                    "" +
                    "data-book-id=" + item.id + "\">\n" +
                    "\n" +
                    "Delete</button></td>")
                $("#feedback").append(tr);

            })
        }
    });

}

