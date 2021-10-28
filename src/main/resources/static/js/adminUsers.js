const usersList = document.getElementById('usersList');
const searchBarUser = document.getElementById('searchBarUsers');
let hpUser = [];

const editFormUser = document.querySelector('.editFormUser')
const btnSubUser = document.querySelector('.subBTNUser')

const deleteFormUser = document.querySelector('.deleteFormUser')
const btnDelUser = document.querySelector('.delBTNUser')

const btnCreateUser = document.querySelector('.createBTNUser')

const urlUser = "http://localhost:8888/adminapi/users";



searchBarUser.addEventListener('keyup', (e) => {
    const searchString = e.target.value.toLowerCase();

    const filtered = hpUser.filter((entity) => {
        return (
            entity.firstName.toLowerCase().includes(searchString)
        );
    });
    displayUsers(filtered);
    loadUsersModals(filtered);
});

const loadUsers = async () => {
    const res = await fetch(urlUser);
    hpUser = await res.json();
    displayUsers(hpUser);
    loadUsersModals(hpUser)
};

const loadUsersModals = (list) => {
    list.forEach(entity => {
        const btnEdit = document.querySelector(`#dataIdUser${entity.id} .btn-info`);
        btnEdit.addEventListener('click', () => {
            editFormUser.id.value = entity.id
            editFormUser.name.value = entity.firstName
        })

        const btnDelete = document.querySelector(`#dataIdUser${entity.id} .btn-danger`);
        btnDelete.addEventListener('click', () => {
            deleteFormUser.id.value = entity.id
            deleteFormUser.name.value = entity.firstName
        })
    })
};

const displayUsers = (list) => {
    usersList.innerHTML = list
        .map((user) => {
            return `
            <tr id="dataIdUser${user.id}">
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td><button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#editModalUser">Edit</button></td>
                <td><button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModalUser">Delete</button></td>
            </tr>
        `;
        })
        .join('');
};

btnDelUser.addEventListener('click', async (e) => {
    e.preventDefault();
    let id = document.getElementById('deleteIdUser').value;
    let delURL = urlUser + '/' + id;
    await fetch(delURL, {
        method: 'DELETE'
    }).then((res) => {
        res.json()
        loadUsers()
    })

})

btnSubUser.addEventListener('click', async (e) => {
    e.preventDefault();
    await fetch(urlUser, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.getElementById('editIdUser').value,
            firstName: document.getElementById('editNameUser').value,
        })
    }).then(res => {
        res.json()
        loadUsers()
    })
})

btnCreateUser.addEventListener('click', async (e) => {
    e.preventDefault();
    await fetch(urlUser, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstName: document.getElementById('createNameUser').value,
        })
    }).then(res => {
        res.json();
        loadUsers()
    })
})

loadUsers().then();