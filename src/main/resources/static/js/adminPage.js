const apiUsersUri = 'http://localhost:8080/rest/users/'

$(document).ready(function () {

    let requestOptions = {
        method: 'GET',
        body: null
    };

    fillUsersTable(sendFetchRequest(apiUsersUri, requestOptions));

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        const dataId = $(this).attr('data-id');
        console.log(typeof dataId)
        console.log(dataId)
        // const data = $(this).attr('data');
        // console.log(typeof data)
        // console.log(data)
        $.get("/admin/" + dataId, function (user, status) {
            console.log(user)
            console.log(typeof user)
            $('.myFormDelete #deleteId').val(user.id);
            $('.myFormDelete #deleteFirstName').val(user.firstName);
            $('.myFormDelete #deleteLastName').val(user.lastName);
            $('.myFormDelete #deleteEmail').val(user.email);
            $('.myFormDelete #formDelete').attr('action', "/admin/" + dataId);
        });
        $('.myFormDelete #deleteModal').modal();
    });
})

let fillUsersTable = function (listAllUsers) {
    listAllUsers.then(function (data) {
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
    let promiseUser = sendFetchRequest(apiUsersUri + id, requestOptions);
    let fillForm = function (promiseUser) {
        promiseUser.then(function (user) {
            console.log(user)
            let bookForm = modal.find('.bookForm');
            $('.myFormEdit #editId').val(user.id);
            $('.myFormEdit #editFirstName').val(user.firstName);
            $('.myFormEdit #editLastName').val(user.lastName);
            $('.myFormEdit #editEmail').val(user.email);
            $('.myFormEdit #editRoles').val(user.roles);
            $("#editRoles option:last").prop('selected', true);
        })
            .catch(function (error) {
                console.log("Error " + error);
            })
    }

}


function getUserDelete(id) {
    console.log(id)
    let requestOptions = {
        method: 'GET',
        body: null
    };
    let promiseUser = sendFetchRequest(apiUsersUri + id, requestOptions);
    let fillForm = function (promiseUser) {
        promiseUser.then(function (user) {
            console.log(user)
            $('.myFormDelete #deleteId').val(user.id);
            $('.myFormDelete #deleteFirstName').val(user.firstName);
            $('.myFormDelete #deleteLastName').val(user.lastName);
            $('.myFormDelete #deleteEmail').val(user.email);
            $("#editRoles option:last").prop('selected', true);
        })
    }
}