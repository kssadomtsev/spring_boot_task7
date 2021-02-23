const apiUserUri = 'http://localhost:8080/rest/users/'

$(document).ready(function () {
    const dataId = $("#tData").attr('data-id');
    console.log(dataId)

    let requestOptions = {
        method: 'GET',
        body: null
    };

    let userPromise = sendFetchRequest(apiUserUri + dataId, requestOptions);
    fillPersonalData(userPromise)
})