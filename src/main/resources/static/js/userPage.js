$(document).ready(function () {
    const dataId = $("#tData").attr('data-id');
    const apiUserUri = 'http://localhost:8080/rest/users/' + dataId
    console.log(dataId)

    let requestOptions = {
        method: 'GET',
        body: null,
        redirect: 'follow'
    };

    let userPromise = sendFetchRequest(apiUserUri, requestOptions);
    fillPersonalData(userPromise)
})