let sendFetchRequest = function (requestUrl, requestOptions) {
    console.log(requestUrl)
    console.log(requestOptions)
    return fetch(requestUrl, requestOptions)
        .then(response => {
            return response.json()
        })
        .then(result => {
            console.log(result)
            return result;
        })
        .catch(error => console.log('error', error));
}