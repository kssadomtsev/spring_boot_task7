let fillUserData = function (user) {
    $("#tData").append(
        '<tbody>' +
        '<tr>' +
        "<td>" + user.id + "</td>" +
        "<td>" + user.firstName + "</td>" +
        "<td>" + user.lastName + "</td>" +
        "<td>" + user.email + "</td>" +
        "<td>" + getRolesUserString(user) + "</td>" +
        +"</tr>" +
        ' </tbody>'
    );
}