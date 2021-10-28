const shopsList = document.getElementById('shopsList');
const searchBarShop = document.getElementById('searchBarShops');
let hpShop = [];

const editFormShop = document.querySelector('.editFormShop')
const btnSubShop = document.querySelector('.subBTNShop')

const deleteFormShop = document.querySelector('.deleteFormShop')
const btnDelShop = document.querySelector('.delBTNShop')

const btnCreateShop = document.querySelector('.createBTNShop')

const urlShop = "http://localhost:8888/adminapi/shops";



searchBarShop.addEventListener('keyup', (e) => {
    const searchString = e.target.value.toLowerCase();

    const filtered = hpShop.filter((entity) => {
        return (
            entity.name.toLowerCase().includes(searchString)
        );
    });
    displayShops(filtered);
    loadShopsModals(filtered)
});

const loadShops = async () => {
    const res = await fetch(urlShop);
    hpShop = await res.json();
    displayShops(hpShop)
    loadShopsModals(hpShop)
};

const loadShopsModals = (list) => {
    list.forEach(entity => {
        const btnEdit = document.querySelector(`#dataIdShop${entity.id} .btn-info`);
        btnEdit.addEventListener('click', () => {
            editFormShop.id.value = entity.id
            editFormShop.name.value = entity.name
        })

        const btnDelete = document.querySelector(`#dataIdShop${entity.id} .btn-danger`);
        btnDelete.addEventListener('click', () => {
            deleteFormShop.id.value = entity.id
            deleteFormShop.name.value = entity.name
        })
    })
};

const displayShops = (list) => {
    shopsList.innerHTML = list
        .map((shop) => {
            return `
            <tr id="dataIdShop${shop.id}">
                <td>${shop.id}</td>
                <td>${shop.name}</td>
                <td><button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#editModalShop">Edit</button></td>
                <td><button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModalShop">Delete</button></td>
            </tr>
        `;
        })
        .join('');
};

btnDelShop.addEventListener('click', async (e) => {
    e.preventDefault();
    let id = document.getElementById('deleteIdShop').value;
    let delURL = urlShop + '/' + id;
    await fetch(delURL, {
        method: 'DELETE'
    }).then((res) => {
        res.json()
        loadShops()
    })

})

btnSubShop.addEventListener('click', async (e) => {
    e.preventDefault();
    await fetch(urlShop, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.getElementById('editIdShop').value,
            name: document.getElementById('editNameShop').value,
        })
    }).then(res => {
        res.json()
        loadShops()
    })
})

btnCreateShop.addEventListener('click', async (e) => {
    e.preventDefault();
    await fetch(urlShop, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: document.getElementById('createNameShop').value,
        })
    }).then(res => {
        res.json();
        loadShops()
    })
})

loadShops().then();