
// Send request function
async function sendRequest(method, url, body = null) {
    let response
    const headers = {'Content-Type': 'application/json'}
    if (method === "GET") {
        response = await fetch(url, {
            method: method,
            headers: headers
        })
    } else {
        response = await fetch(url, {
            method: method,
            body: JSON.stringify(body),
            headers: headers
        })
    }
    if (response.ok) {
        return response.json()
    } else {
        alert("Ошибка HTTP: " + response.status);
    }
}

function getUserPrincipal() {
    return sendRequest('GET', "/api/users/principal")
}

async function updateUser() {
    sendRequest('PUT', "/api/users/",  tempUser).then(resp=>{
        console.log("sendrequest")
        console.log(user)
        console.log(tempUser)
        if(resp.result === "OK")   user= JSON.parse(JSON.stringify(tempUser));
        console.log("resp")
        console.log(user)
        console.log(tempUser)
        writeUserData()
    })
}
