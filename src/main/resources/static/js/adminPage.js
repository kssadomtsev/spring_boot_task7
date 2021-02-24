const apiUsersUri = 'http://localhost:8080/rest/users/'

$(document).ready(function () {
    fillUsersTable();
})

let fillUsersTable = function () {
    let requestOptions = {
        method: 'GET',
        body: null
    };

    sendFetchRequest(apiUsersUri, requestOptions).then(function (data) {
        $("#tDataUsers").empty();

        data.forEach(function (user) {
            $("#tDataUsers").append(
                '<tbody>' +
                "<td>" + user.id + "</td>" +
                "<td>" + user.firstName + "</td>" +
                "<td>" + user.lastName + "</td>" +
                "<td>" + user.email + "</td>" +
                "<td>" + getRolesUserString(user) + "</td>" +
                '<td><button type="button" onclick=getUserEdit(' + user.id + ') class="btn btn-primary eBtn" data-toggle="modal" data-target="#editModal" data-id=' + user.id + '> Edit</button></td>' +
                '<td><button type="button" onclick=getUserDelete(' + user.id + ') class="btn btn-danger delBtn" data-toggle="modal" data-target="#deleteModal" data-id=' + user.id + '>Delete</button></td>'
                + "</tr>" +
                ' </tbody>'
            );
            console.log(user)

        })
    })
}

function getUserEdit(id) {

    let requestOptions = {
        method: 'GET',
        body: null
    };
    let fillForm = function (promiseUser) {
        promiseUser.then(function (user) {
            $('.myFormEdit #editId').val(user.id);
            $('.myFormEdit #editFirstName').val(user.firstName);
            $('.myFormEdit #editLastName').val(user.lastName);
            $('.myFormEdit #editEmail').val(user.email);
            $('.myFormEdit #editPassword').val("");
            $('.myFormEdit #editRoles').val(user.roles);
        })
            .catch(function (error) {
                console.log("Error " + error);
            })
    }
    fillForm(sendFetchRequest(apiUsersUri + id, requestOptions))
}

function editUser() {
    let id = $("#editId").val();

    let user = {
        id: id,
        firstName: $("#editFirstName").val(),
        lastName: $("#editLastName").val(),
        email: $("#editEmail").val(),
        password: $("#editPassword").val(),
        roles: $("#editRoles").val(),
    }

    let requestOptions = {
        method: 'PUT',
        body: JSON.stringify(user),
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    };
    sendFetchRequest(apiUsersUri + id, requestOptions)
        .then(function () {
            fillUsersTable();
        })
}



function getUserDelete(id) {
    console.log(id)
    let requestOptions = {
        method: 'GET',
        body: null
    };
    let fillForm = function (promiseUser) {
        promiseUser.then(function (user) {
            console.log(user)
            $('.myFormDelete #deleteId').val(user.id);
            $('.myFormDelete #deleteFirstName').val(user.firstName);
            $('.myFormDelete #deleteLastName').val(user.lastName);
            $('.myFormDelete #deleteEmail').val(user.email);
            $('.myFormDelete #deleteRoles').val(user.roles);
        })
            .catch(function (error) {
                console.log("Error " + error);
            })
    }
    fillForm(sendFetchRequest(apiUsersUri + id, requestOptions))
}

function deleteUser() {
    let id = $("#deleteId").val();

    let requestOptions = {
        method: 'DELETE',
        body: null
    };
    sendFetchRequest(apiUsersUri + id, requestOptions)
        .then(function () {
            fillUsersTable();
        })
}

function addUser() {

    let user = {
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        roles: $("#roles").val(),
    }

    let requestOptions = {
        method: 'POST',
        body: JSON.stringify(user),
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    };
    sendFetchRequest(apiUsersUri, requestOptions)
        .then(function () {
            fillUsersTable();
            $('#usersTable').addClass("show active");
            $('#navAllUsers').addClass('active');
            $('#newUser').removeClass("show active");
            $('#navNewUser').removeClass("active");
            $(':input','#formNew')
                .not(':button, :submit, :reset, :hidden')
                .val('')
                .prop('checked', false)
                .prop('selected', false);
        })
}

function fillUserInfo(){
    const dataId = $("#tData").attr('data-id');

    let requestOptions = {
        method: 'GET',
        body: null
    };

    sendFetchRequest(apiUsersUri + dataId, requestOptions)
        .then(function (user){
            console.log(user)
            $("#tData").empty();
            fillUserData(user)
            $('#v-pills-User').addClass("show active");
            $('#v-pills-user-tab').addClass("active");
            $('#v-pills-UserTable').removeClass("show active");
            $('#v-pills-admin-tab').removeClass("active");
        });
}
