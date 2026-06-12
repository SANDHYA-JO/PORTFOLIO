<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ShopHub E-Commerce Catalog</title>

<style>
*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:Arial,sans-serif;
}

body{
background:#f4f4f4;
}

header{
background:#222;
color:white;
padding:15px 30px;
display:flex;
justify-content:space-between;
align-items:center;
}

header h1{
font-size:28px;
}

nav a{
color:white;
text-decoration:none;
margin-left:20px;
font-weight:bold;
}

nav a:hover{
color:#00bfff;
}

.container{
padding:20px;
}

.products{
display:grid;
grid-template-columns:repeat(auto-fit,minmax(250px,1fr));
gap:20px;
margin-top:20px;
}

.card{
background:white;
padding:15px;
border-radius:10px;
box-shadow:0 2px 8px rgba(0,0,0,0.1);
text-align:center;
}

.card img{
width:100%;
height:200px;
object-fit:cover;
border-radius:8px;
}

.card h3{
margin:10px 0;
}

.card p{
font-size:18px;
font-weight:bold;
color:green;
}

button{
padding:10px 15px;
border:none;
background:#007bff;
color:white;
border-radius:5px;
cursor:pointer;
margin-top:10px;
}

button:hover{
background:#0056b3;
}

.cart-item{
background:white;
padding:10px;
margin-bottom:10px;
border-radius:8px;
}

.total{
margin-top:20px;
font-size:22px;
font-weight:bold;
color:green;
}

.hero{
text-align:center;
padding:50px;
}

.hero h2{
font-size:36px;
margin-bottom:10px;
}

.hero p{
font-size:18px;
color:#555;
}
</style>
</head>

<body>

<header>
<h1>ShopHub</h1>

<nav>
<a href="#/">Home</a>
<a href="#/products">Products</a>
<a href="#/cart">Cart</a>
</nav>
</header>

<div id="app"></div>

<script>

const products = [
{
id:1,
name:"Laptop",
price:50000,
image:"https://picsum.photos/400?1"
},
{
id:2,
name:"Smartphone",
price:25000,
image:"https://picsum.photos/400?2"
},
{
id:3,
name:"Headphones",
price:5000,
image:"https://picsum.photos/400?3"
},
{
id:4,
name:"Smart Watch",
price:8000,
image:"https://picsum.photos/400?4"
},
{
id:5,
name:"Camera",
price:35000,
image:"https://picsum.photos/400?5"
},
{
id:6,
name:"Gaming Mouse",
price:2500,
image:"https://picsum.photos/400?6"
}
];

const app = document.getElementById("app");

function getCart(){
return JSON.parse(localStorage.getItem("cart")) || [];
}

function saveCart(cart){
localStorage.setItem("cart", JSON.stringify(cart));
}

function addToCart(id){

const cart = getCart();

const product = products.find(item => item.id === id);

cart.push(product);

saveCart(cart);

alert(product.name + " added to cart!");

}

function homePage(){

app.innerHTML = `
<div class="hero">
<h2>Welcome to ShopHub</h2>
<p>Your One Stop Online Shopping Destination</p>
<br>
<a href="#/products">
<button>Shop Now</button>
</a>
</div>
`;

}

function productsPage(){

let html = `
<div class="container">
<h2>Our Products</h2>

<div class="products">
`;

products.forEach(product => {

html += `
<div class="card">

<img loading="lazy" src="${product.image}" alt="${product.name}">

<h3>${product.name}</h3>

<p>₹${product.price}</p>

<button onclick="addToCart(${product.id})">
Add To Cart
</button>

</div>
`;

});

html += `
</div>
</div>
`;

app.innerHTML = html;

}

function cartPage(){

const cart = getCart();

let total = 0;

let html = `
<div class="container">
<h2>Shopping Cart</h2>
<br>
`;

if(cart.length === 0){

html += `<p>Your cart is empty.</p>`;

}
else{

cart.forEach(item=>{

total += item.price;

html += `
<div class="cart-item">
${item.name} - ₹${item.price}
</div>
`;

});

html += `
<div class="total">
Total: ₹${total}
</div>

<br>

<button onclick="clearCart()">
Clear Cart
</button>
`;
}

html += `</div>`;

app.innerHTML = html;

}

function clearCart(){

localStorage.removeItem("cart");

cartPage();

}

function router(){

const route = location.hash || "#/";

if(route === "#/"){
homePage();
}
else if(route === "#/products"){
productsPage();
}
else if(route === "#/cart"){
cartPage();
}

}

window.addEventListener("load", router);
window.addEventListener("hashchange", router);

</script>

</body>
</html>
