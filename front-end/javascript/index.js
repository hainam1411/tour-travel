function showAllTour() {
    $.ajax({
        type: "GET",
        dataType: "json",
        headers: { Accept: "application/json" },
        url: "http://localhost:8080/api/tours",
        success: function (data) {
            console.log(data);

            let content = "";
            for (let index = 0; index < data.length; index++) {
                content += `<tr>
            <td>${index + 1}</td>
            <td>${data[index].code}</td>
            <td>${data[index].name}</td>
            <td>${data[index].price}</td>
            <td>${data[index].type.name}</td>

            <td>
            <button class="btn btn-primary" 
            onclick="editTour(${data[index].id})">Sửa</button>
            </td>

            <td>
            <button class="btn btn-danger
            onclick="deleteTour(${data[index].tourId})">Xóa</button>
            </td>
          </tr>`;
            }
            document.getElementById("content").innerHTML = content;
        },
    });
}
showAllTour();


function createTour(){
    let code = document.getElementById("code").value;
    let name = document.getElementById("name").value;
    let price = document.getElementById("price").value;
    let type = +document.getElementById("type").value;

    // let tour =
    //     {
    //         "code": code,
    //         "name": name,
    //         "price": price,
    //         "type": {
    //             "id": type
    //         }
    //     };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: `http://localhost:8080/api/tours`,
        data: JSON.stringify({
            code: code,
            name: name,
            price: price,
            type: {
                id: type
            }
        }),
        success: function (response) {
            console.log(response);
            showAllTour();
        },
        error: function (error) {
            console.error(error);
        }
    });
}

function editTour(id) {
    $.ajax({
        url: `http://localhost:8080/api/tours/${id}`,
        type: "GET",
        // headers:{accept: "application/json", contentType: "application/json"},
        dataType: "json",
        success: function (dl) {
            console.log(dl);
            let content =
            document.getElementById("editTour").innerHTML = `
      <div>
        <label for="">Mã tour</label>
        <input type="text" id="code" value='${dl.code}'>
      </div>
      <div>
        <label for=""> Tên tour</label>
        <input type="text" id="name" value='${dl.name}'>
      </div>
      
      <div>
        <label for="">Giá</label>
        <input type="text" id="price" value='${dl.price}'>
      </div>
      
      
      <div>
        <label for="">Loại tour</label>
        <input type="text" id="typeID" value='${dl.type.name}'>
      </div>
      <button  onclick="updateTour(${dl.tourId})">Update</button>
      <br>
      <br>
      <br>
            `;
        },
    });
}

function deleteTour(id){
    $.ajax({
        url: `http://localhost:8080/api/tours/${id}`,
        type: "DELETE",
        success: function (response) {
            console.log(response);
            showAllTour();
        },
        error: function (error) {
            console.error(error);
        }
    });
}