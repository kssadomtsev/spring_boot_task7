$(document).ready(function () {

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        const dataId = $(this).attr('data-id');
        console.log(typeof dataId)
        console.log(dataId)

        $.get("/admin/" + dataId, function (user, status) {
            console.log(user)
            $('.myFormDelete #deleteId').val(user.id);
            $('.myFormDelete #deleteFirstName').val(user.firstName);
            $('.myFormDelete #deleteLastName').val(user.lastName);
            $('.myFormDelete #deleteEmail').val(user.email);
            $('.myFormDelete #deleteRoles').val(user.roles);
            $('.myFormDelete #formDelete').attr('action', "/admin/" + dataId);
            // $('.myFormUpdate #username').val(users.username);
            // $('.myFormUpdate #password').val(users.password);
            // $('.myFormUpdate #email').val(users.email);
        });
        $('.myFormDelete #deleteModal').modal();
        // const action = $(this).attr('action');
        // console.log(typeof action)
        // console.log(action)
        // $('.myFormDelete #deleteId').val("1111");
        // $('#deleteModal').modal();

        // $('.myFormDelete #username').val(users.username);
        //             $('.myFormUpdate #password').val(users.password);
        //             $('.myFormUpdate #email').val(users.email);
        // const data = $(this).attr('data');
        // $('#removeModalCenter #delRef').attr('href', href);
        // $('#removeModalCenter').modal();
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