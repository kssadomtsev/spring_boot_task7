let fillPersonalData = function (user) {
    user.then(function (data) {
        $("#tData").append(
            '<tbody>' +
            '<tr>' +
            "<td>" + data.id + "</td>" +
            "<td>" + data.firstName + "</td>" +
            "<td>" + data.lastName + "</td>" +
            "<td>" + data.email + "</td>" +
            "<td>" + getRolesUserString(data) + "</td>" +
            +"</tr>" +
            ' </tbody>'
        );
    })
}