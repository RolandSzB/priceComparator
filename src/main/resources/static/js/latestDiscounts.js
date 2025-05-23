function loadLatestDiscounts() {
    fetch('/api/latest-discounts')
      .then(response => response.json())
      .then(data => {
        const latestBody = document.getElementById("latestDiscountsBody");
        latestBody.innerHTML = "";

        if (data.length === 0) {
          latestBody.innerHTML = "<tr><td colspan='3'>No recent discounts found</td></tr>";
          return;
        }

        data.forEach(product => {
          const row = document.createElement("tr");
          row.innerHTML = `
            <td>${product.storeName}</td>
            <td>${product.productName}</td>
            <td>${product.discountPercentage.toFixed(2)}%</td>
          `;
          latestBody.appendChild(row);
        });
      })
      .catch(error => {
        console.error("Failed to load latest discounts:", error);
      });
  }

  loadLatestDiscounts();

