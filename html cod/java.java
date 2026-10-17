const products = [
  {
    id: 1,
    name: "bag",
    price: 1200,
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSh7aksUmXWZih_HHEFKCnvr2ZqWhKsGFzNQ1nyrGWAZA&s=10"
  },
  {
    id: 2,
    name: "Smart Watch",
    price: 4500,
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIWW0OnoN4rOWlEhR8MSmZ_YKSR81p0kdsO2jJOIIyyw&s=10"
  },
  {
    id: 3,
    name: "Stylish Sneakers",
    price: 3500,
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPJkLonvIUh8aKfMtq73m4qtuPDpqNrIewK_2e5c6cIg&s=10"
  }
  
  ,
  {
    id: 4,
    name: "Wireless Headphones",
    price: 2500,
   image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiEEIhqavszcx4KWPf2J5AVLL099ehrfbyk1yJrZepLw&s"
  },
  {
    id: 6,
    name: "iPhone",
    price: 180000,
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBpMvHSXJuHQF7Hzpg5cGvryEMJPXfeQ0zFPbEc1F19w&s=10"
  },
  {
    id: 7,
    name: "Laptop",
    price: 150000,
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6qUKLD_XVRbtm0kM-VjTneRMSWRMU1EN32Zq3_3k6Ng&s=10"
  },
  {
    id: 8,
    name: "Rubik's Cube",
    price: 300,
     image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvZ_vqW6jgWJvPSZe0OJ3Po0MKK5A2J4p6qv23KJRqxg&s=10"
  },
  {
    id: 9,
    name: "Gaming PC",
    price: 250000,
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSovF4HSKEeyi7gBFXW-Om_SXosE344sgtcmTXdGWxXMA&s=10"
  },
  {
    id: 10,
    name: "Smart Pencil",
    price: 2500,
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmyQPd117iAGzZVy-oTkV0TAocdtQHyfz4_LbmH4M7Jw&s"
  }
 
  ,
  {
    id: 11,
    name: "Tablet",
    price: 45000,
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3_x_kFYB2eNecWzRLitzo-X955x6ZBWQw1Nth-cz-CQ&s=10"
  },
  {
    id: 12,
    name: "Action Figure Goku",
    price: 3500,
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiFUkXH6hHhfDBVTl45Q0b0UPXq0PipQKKg4MhOshAAw&s=10"
  },
  {
    id: 13,
    name: "Samsung S23 Ultra",
    price: 180000,
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhJBiY0XgQ5EOxnzteotQUgauxPKfjDN4E7uACb7r80A&s=10"
  }

  
];

let cart = JSON.parse(localStorage.getItem("luxenexaCart")) || [];

const productList = document.getElementById("product-list");
const cartCount = document.getElementById("cart-count");
const cartButton = document.getElementById("cart-btn");
function saveCart() {
  localStorage.setItem("luxenexaCart", JSON.stringify(cart));
}

function updateCartCount() {
  cartCount.textContent = cart.length;
}

updateCartCount();
function displayProducts() {
  productList.innerHTML = products.map(product => `
    <article class="product-card">

      <a href="product.html?id=${product.id}"
         style="display:block; color:inherit; text-decoration:none;">

        <div style="text-align:center;">
          <img src="${product.image}"
               alt="${product.name}"
               style="width:100%; height:220px; object-fit:contain;">
        </div>

        <h3>${product.name}</h3>
      </a>

      <p>Quality product for everyday use.</p>

      <p class="product-price">
        Rs. ${product.price.toLocaleString("en-PK")}
      </p>

      <button class="add-btn" data-product-id="${product.id}">
        Add to Cart
      </button>

      <a href="product.html?id=${product.id}"
         style="display:block; text-align:center; margin-top:12px; color:#d95e0b; font-weight:bold;">
        View Details →
      </a>

    </article>
  `).join("");
}
displayProducts();

productList.addEventListener("click", function(event) {
  const button = event.target.closest("[data-product-id]");
  if (!button) return;

  const productId = Number(button.dataset.productId);
  const product = products.find(item => item.id === productId);

  if (!product) return;

  cart.push(product);
  saveCart();
  updateCartCount();

  button.textContent = "Added ✓";

  setTimeout(() => {
    button.textContent = "Add to Cart";
  }, 800);
});

cartButton.addEventListener("click", function() {
  if (cart.length === 0) {
    alert("Your cart is empty!");
    return;
  }

  let message = "YOUR LUXANEXA CART\n\n";

  cart.forEach((item, index) => {
    message += `${index + 1}. ${item.name} - Rs. ${item.price.toLocaleString("en-PK")}\n`;
  });

  const total = cart.reduce((sum, item) => sum + item.price, 0);
  const removeItem = confirm(
    message + `\nTotal: Rs. ${total.toLocaleString("en-PK")}\n\nOK = Remove last product\nCancel = Keep cart`
  );

  if (removeItem) {
    cart.pop();
    saveCart();
    updateCartCount();
    alert("Last product removed!");
  }
});



(async function () {
  const { createClient } = await import(
    'https://cdn.jsdelivr.net/npm/@supabase/supabase-js@2/+esm'
  );

  const supabase = createClient(
    'https://cnpjzawtkzofkstqrkkc.supabase.co',
    'sb_publishable_77YDSZ4YVDkhCnB5iJH5CQ_fZjY-t4u'
  );

  const loginLink = document.getElementById('loginLink');
  const accountArea = document.getElementById('accountArea');
  const profileButton = document.getElementById('profileButton');
  const accountMenu = document.getElementById('accountMenu');
  const profileAvatar = document.getElementById('profileAvatar');
  const profileName = document.getElementById('profileName');
  const profileEmail = document.getElementById('profileEmail');
  const fullName = document.getElementById('fullName');
  const avatarUpload = document.getElementById('avatarUpload');
  const accountMessage = document.getElementById('accountMessage');

  function showMessage(text) {
    if (accountMessage) accountMessage.textContent = text;
  }

  // Check whether the user is logged in
  async function loadAccount() {
    const { data, error } = await supabase.auth.getUser();

    if (error || !data.user) {
      if (loginLink) loginLink.hidden = false;
      if (accountArea) accountArea.hidden = true;
      return;
    }

    const user = data.user;
    const name = user.user_metadata?.full_name || 'My Account';

    if (loginLink) loginLink.hidden = true;
    if (accountArea) accountArea.hidden = false;
    if (profileName) profileName.textContent = name;
    if (profileEmail) profileEmail.textContent = user.email || '';
    if (fullName) fullName.value = user.user_metadata?.full_name || '';
  }

  // Open and close the profile menu
  profileButton?.addEventListener('click', () => {
    accountMenu.hidden = !accountMenu.hidden;
  });

  // Preview the selected profile picture
  avatarUpload?.addEventListener('change', () => {
    const file = avatarUpload.files?.[0];
    if (!file) return;

    if (!file.type.startsWith('image/')) {
      showMessage('Please select an image file.');
      avatarUpload.value = '';
      return;
    }

    if (file.size > 2 * 1024 * 1024) {
      showMessage('Image must be smaller than 2 MB.');
      avatarUpload.value = '';
      return;
    }

    profileAvatar.src = URL.createObjectURL(file);
    showMessage('Picture preview updated. Permanent saving needs Storage setup.');
  });

  // Save the user's name to Supabase
  document.getElementById('saveProfile')?.addEventListener('click', async () => {
    const name = fullName.value.trim();

    if (!name) {
      showMessage('Please enter your name.');
      return;
    }

    showMessage('Saving profile...');

    const { data, error } = await supabase.auth.updateUser({
      data: { full_name: name }
    });

    if (error) {
      showMessage(error.message);
      return;
    }

    profileName.textContent = data.user.user_metadata.full_name;
    showMessage('Profile name saved!');
  });

  // Change password for the logged-in user
  document.getElementById('changePassword')?.addEventListener('click', async () => {
    const password = document.getElementById('newPassword').value;

    if (password.length < 6) {
      showMessage('Password must be at least 6 characters.');
      return;
    }

    showMessage('Updating password...');

    const { error } = await supabase.auth.updateUser({ password });

    if (error) {
      showMessage(error.message);
      return;
    }

    document.getElementById('newPassword').value = '';
    showMessage('Password changed successfully!');
  });

  // Log out
  document.getElementById('logoutButton')?.addEventListener('click', async () => {
    showMessage('Logging out...');

    const { error } = await supabase.auth.signOut();

    if (error) {
      showMessage(error.message);
      return;
    }

    window.location.href = 'login.html';
  });

  await loadAccount();

  // Update the menu if the login state changes
  supabase.auth.onAuthStateChange(() => {
    loadAccount();
  });

})().catch((error) => {
  console.error('LUXANEXA account error:', error);
});
