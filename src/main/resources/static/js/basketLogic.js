  function optimizeBasket() {
        var basketItems = document.getElementById("basketInput").value.split(",");
        var trimmedItems = basketItems.map(item => item.trim()).filter(item => item.length > 0);

        if (trimmedItems.length === 0) {
            alert("Please enter at least one product.");
            return;
        }

        fetch("/api/basket/optimize", {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(trimmedItems)
        })
        .then(response => response.json())
        .then(data => {
            let resultsDiv = document.getElementById("optimizedResults");
            resultsDiv.innerHTML = "";
            data.forEach(offer => {
                let priceDisplay = "";
                if (offer.discountPercentage > 0) {
                    let discountPrice = offer.price * (1 - offer.discountPercentage / 100);
                    priceDisplay = `
                        <span class="original-price">${offer.price.toFixed(2)} ${offer.currency}</span>
                        <span class="discount-price">${discountPrice.toFixed(2)} ${offer.currency}</span>
                        (${offer.discountPercentage}% off)
                    `;
                } else {
                    priceDisplay = `${offer.price.toFixed(2)} ${offer.currency}`;
                }

                resultsDiv.innerHTML += `
                    <div>
                        <b>${offer.productName}</b> at <span class="store-name">${offer.storeName}</span>:
                        ${priceDisplay}
                    </div>
                `;
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert("An error occurred while optimizing the basket.");
        });
    }