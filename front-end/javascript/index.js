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
          </tr>`;
            }
            document.getElementById("content").innerHTML = content;
        },
    });
}
showAllTour();