$(document).ready(function () {

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

    $('table .eBtn').on('click', function (event) {
        event.preventDefault();
        const dataId = $(this).attr('data-id');
        console.log(typeof dataId)
        console.log(dataId)
        const data = $(this).attr('data');
        console.log(typeof data)
        console.log(data)
        $('.myFormEdit #formEdit').attr('action', "/admin/" + dataId);
        // $('.myFormEdit #formEdit').attr('object', data);
        // $('.myFormEdit #editFirstName').attr('field', "*{firstName}");
        // $('.myFormEdit #editLastName').attr('field', "*{lastName}");
        // $('.myFormEdit #editEmail').attr('field', "*{email}");
        // $('.myFormEdit #editPassword').attr('field', "*{password}");
        // $('.myFormEdit #editRoles').attr('field', "*{roles}");

        $.get("/admin/" + dataId, function (user, status) {
            console.log(user)
            console.log(typeof user)
            $('.myFormEdit #editId').val(user.id);
            $('.myFormEdit #editFirstName').val(user.firstName);
            $('.myFormEdit #editLastName').val(user.lastName);
            $('.myFormEdit #editEmail').val(user.email);
        });
        $('.myFormEdit #editModal').modal();
    });
});


// $(document).ready(function(){
//
//     $('.table .eBtn, .table .delBtn').on('click', function(event) {
//         event.preventDefault();
//         const data = $(this).attr('data');
//         const dataTarget = $(this).attr('data-target');
//         console.log(typeof data)
//         console.log(data)
//         console.log(dataTarget)
//         const modal = document.getElementById(dataTarget.substring(1))
//         console.log(modal)
//         console.log(data.id)
//         console.log(data.firstName)
//         console.log(data.lastName)
//         $("."+dataTarget.substring(1) +" #deleteId").val(data.id);
//         $("."+dataTarget.substring(1) +" #deleteUsername").val(data.firstName);
//         $("."+dataTarget.substring(1) +" #deleteLastname").val(data.lastName);
//         $("."+dataTarget.substring(1) +" #deleteEmail").val(data.email);
//         $("."+dataTarget.substring(1) +" #deleteRoles").val(data.role);
//         console.log(modal)
//         $(dataTarget).modal('hide');
//     var text = $(this).text();
//     //for update user
//     if (text == 'Edit') {
//         $.get(href, function (users, status) {
//             $('.myFormUpdate #id').val(users.id);
//             $('.myFormUpdate #username').val(users.username);
//             $('.myFormUpdate #password').val(users.password);
//             $('.myFormUpdate #email').val(users.email);
//         });
//         $('.myFormUpdate #updateModal').modal();
//     } else {
//         //for creating user
//         $('.myFormCreate #username').val('');
//         $('.myFormCreate #password').val('');
//         $('.myFormCreate #email').val('');
//         $('.myFormCreate #myModalCreate').modal();
//     }
// });
// //for delete user
// $('.table .delBtn').on('click', function(event) {
//     event.preventDefault();
//     var href = $(this).attr('href');
//     $('#removeModalCenter #delRef').attr('href', href);
//     $('#removeModalCenter').modal();
//     });
// });