document.addEventListener("DOMContentLoaded", function () {
    fetch('/api/top-discounts')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('topDiscountsBody');

            data.forEach(offer => {
                const discountedPrice = offer.price * (1 - offer.discountPercentage / 100);
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${offer.storeName}</td>
                    <td>${offer.productName}</td>
                    <td><span style="color:red; text-decoration:line-through;">${offer.price.toFixed(2)}</span> ${offer.currency}</td>
                    <td>${offer.discountPercentage}%</td>
                    <td><strong>${discountedPrice.toFixed(2)} ${offer.currency}</strong></td>
                `;

                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Failed to fetch top discounts:", error));
});
