const apiUserUri = 'http://localhost:8080/rest/users/'

$(document).ready(function () {
    const dataId = $("#tData").attr('data-id');

    let requestOptions = {
        method: 'GET',
        body: null
    };

    sendFetchRequest(apiUserUri + dataId, requestOptions)
        .then(function (user) {
            fillUserData(user)
        })
})