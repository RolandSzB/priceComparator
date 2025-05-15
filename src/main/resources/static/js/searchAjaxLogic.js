  function searchProducts() {
      var query = document.getElementById("searchQuery").value;
      var resultsBody = document.getElementById("resultsBody");


      if (query.length < 3) {
          resultsBody.innerHTML = "";
          return;
      }


      fetch(`/api/search?query=${query}`)
          .then(response => response.json())
          .then(data => {
              resultsBody.innerHTML = "";

              if (data.length === 0) {
                  resultsBody.innerHTML = "<tr><td colspan='9'>No offer found</td></tr>";
                  return;
              }


              data.forEach(offer => {
                  var row = document.createElement("tr");

                  row.innerHTML = `
                      <td>${offer.storeName}</td>
                      <td>${offer.productId}</td>
                      <td>${offer.productName}</td>
                      <td>${offer.productCategory}</td>
                      <td>${offer.brand}</td>
                      <td>${offer.packageQuantity}</td>
                      <td>${offer.packageUnit}</td>
                      <td>${offer.price}</td>
                      <td>${offer.currency}</td>
                  `;

                  resultsBody.appendChild(row);
              });
          })
          .catch(error => console.error('Error:', error));
  }