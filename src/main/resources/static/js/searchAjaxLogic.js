function searchProducts() {
      var query = document.getElementById("searchQuery").value;
      var resultsBody = document.getElementById("resultsBody");

      if (query.length < 3) {
          resultsBody.innerHTML = "";
          return;
      }

      fetch(`/api/search?query=${encodeURIComponent(query)}`)
          .then(response => response.json())
          .then(data => {
              resultsBody.innerHTML = "";

              if (data.length === 0) {
                  resultsBody.innerHTML = "<tr><td colspan='9'>No offer found</td></tr>";
                  return;
              }

              data.forEach(offer => {
                  var row = document.createElement("tr");

                  let discountedPrice = offer.price;
                  if (offer.discountPercentage && offer.discountPercentage > 0) {
                      discountedPrice = offer.price * (1 - offer.discountPercentage / 100);
                  }

                  let priceDisplay = "";
                  if (offer.discountPercentage && offer.discountPercentage > 0) {
                      priceDisplay = `<span class="original-price" style="color:red; text-decoration: line-through;">${offer.price.toFixed(2)}</span>
                                      <span class="discount-price" style="color:black; font-weight: bold; margin-left: 8px;">${discountedPrice.toFixed(2)}</span>`;
                  } else {
                      priceDisplay = `${offer.price.toFixed(2)}`;
                  }

                  row.innerHTML = `
                      <td>${offer.storeName}</td>
                      <td>${offer.productId}</td>
                      <td>${offer.productName}</td>
                      <td>${offer.productCategory}</td>
                      <td>${offer.brand}</td>
                      <td>${offer.packageQuantity}</td>
                      <td>${offer.packageUnit}</td>
                      <td>${priceDisplay}</td>
                      <td>${offer.discountPercentage ? offer.discountPercentage.toFixed(2) + '%' : ''}</td>
                      <td>${offer.currency}</td>

                  `;

                  resultsBody.appendChild(row);
              });
          })
          .catch(error => console.error('Error:', error));
  }