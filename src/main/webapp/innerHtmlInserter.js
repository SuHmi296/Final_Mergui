

document.addEventListener('DOMContentLoaded', function() {
    // Set the navigation bar inner HTML
    let navInnerHtml = `
      <div class="flex items-center space-x-4">
        <a href="index.html">
          <img src="./Images/lg.png" alt="Logo" class="w-32 sm:w-40 md:w-48 lg:w-56 h-auto">
        </a>
      </div>
      <ul class="flex space-x-8 text-red-700 font-semibold">
        <li><a href="destination.html" class="hover:text-red-500 transition">Destination</a></li>
        <li><a href="hotelNameOutput.jsp" class="hover:text-red-500 transition">Accommodation</a></li>
        <li><a href="restaurant.html" class="hover:text-red-500 transition">Restaurants</a></li>
        <li><a href="#" class="hover:text-red-500 transition">Transportation</a></li>
        <li><a href="contactus.html" class="hover:text-red-500 transition">Contact Us</a></li>
      </ul>
      <div class="flex space-x-2 items-center">
        <div class="w-60 relative">
          <input type="text" class="bg-gray-100 h-10 px-5 pr-10 w-full rounded-full text-sm focus:outline-none transition-all duration-500 focus:w-64" placeholder="Search...">
          <button type="submit" class="absolute right-0 top-0 mt-3 mr-4 text-red-700">
            <i class="fas fa-search"></i>
          </button>
          <div id="userPopup" class="fixed top-16 right-4 bg-white border rounded-lg shadow-lg p-4 hidden">
            <p>Welcome, <span id="popupUserName"></span>!</p>
            <button onclick="logout()" class="block mt-2 text-red-700 hover:text-red-500">Log Out</button>
          </div>
        </div>
        <div id="userContainer" class="flex items-center space-x-2 cursor-pointer">
          <!-- User SVG or Username will be dynamically added here -->
        </div>
      </div>
    `;

    // Update innerHTML for the navigation container
    document.getElementById("navContainer").innerHTML = navInnerHtml;

    // Display user info or Sign In button
    function displayUserOrSignIn() {
        let isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
        let name = localStorage.getItem('name') || 'User';

        let content = isLoggedIn
			? `<div id="profileContainer" class="flex items-center justify-center cursor-pointer">
					<svg fill="#b8162a" width="35px" height="55px" viewBox="0 0 64 64" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xml:space="preserve" xmlns:serif="http://www.serif.com/" style="fill-rule:evenodd;clip-rule:evenodd;stroke-linejoin:round;stroke-miterlimit:2;" stroke="#b8162a" stroke-width="0.00064"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round" stroke="#CCCCCC" stroke-width="2.56"></g><g id="SVGRepo_iconCarrier"> <g transform="matrix(1,0,0,1,-1216,-192)"> <rect id="Icons" x="0" y="0" width="1280" height="800" style="fill:none;"></rect> <g id="Icons1" serif:id="Icons"> <g id="Strike"> </g> <g id="H1"> </g> <g id="H2"> </g> <g id="H3"> </g> <g id="list-ul"> </g> <g id="hamburger-1"> </g> <g id="hamburger-2"> </g> <g id="list-ol"> </g> <g id="list-task"> </g> <g id="trash"> </g> <g id="vertical-menu"> </g> <g id="horizontal-menu"> </g> <g id="sidebar-2"> </g> <g id="Pen"> </g> <g id="Pen1" serif:id="Pen"> </g> <g id="clock"> </g> <g id="external-link"> </g> <g id="hr"> </g> <g id="info"> </g> <g id="warning"> </g> <g id="plus-circle"> </g> <g id="minus-circle"> </g> <g id="vue"> </g> <g id="cog"> </g> <g id="logo"> </g> <g id="radio-check"> </g> <g id="eye-slash"> </g> <g id="eye"> </g> <g id="toggle-off"> </g> <g id="shredder"> </g> <g id="spinner--loading--dots-" serif:id="spinner [loading, dots]"> </g> <g id="react"> </g> <g id="check-selected"> </g> <g id="turn-off"> </g> <g id="code-block"> </g> <g id="user" transform="matrix(1.03318,0,0,1.03318,-20.8457,199.979)"> <g transform="matrix(0.909091,0,0,0.909091,1182.28,-18.6364)"> <path d="M50,46.5C42.8,46.5 37,40.7 37,33.5C37,26.3 42.8,20.5 50,20.5C57.2,20.5 63,26.3 63,33.5C63,40.7 57.2,46.5 50,46.5ZM50,24.5C45,24.5 41,28.5 41,33.5C41,38.5 45,42.5 50,42.5C55,42.5 59,38.5 59,33.5C59,28.5 55,24.5 50,24.5Z" style="fill-rule:nonzero;"></path> </g> <g transform="matrix(1,0,0,1,1177.7,-20.5)"> <path d="M34.036,58.5L34.036,67L30.4,67L30.4,58.5C30.4,51.318 39.218,45.773 50.4,45.773C61.582,45.773 70.4,51.318 70.4,58.5L70.4,67L66.764,67L66.764,58.5C66.764,53.591 59.309,49.409 50.4,49.409C41.491,49.409 34.036,53.591 34.036,58.5Z" style="fill-rule:nonzero;"></path> </g> </g> <g id="coffee-bean"> </g> <g transform="matrix(0.638317,0.368532,-0.368532,0.638317,785.021,-208.975)"> <g id="coffee-beans"> <g id="coffee-bean1" serif:id="coffee-bean"> </g> </g> </g> <g id="coffee-bean-filled"> </g> <g transform="matrix(0.638317,0.368532,-0.368532,0.638317,913.062,-208.975)"> <g id="coffee-beans-filled"> <g id="coffee-bean2" serif:id="coffee-bean"> </g> </g> </g> <g id="clipboard"> </g> <g transform="matrix(1,0,0,1,128.011,1.35415)"> <g id="clipboard-paste"> </g> </g> <g id="clipboard-copy"> </g> <g id="Layer1"> </g> </g> </g> </g></svg>
              </div>`
            : `<a href="signin.html" id="logout" class="bg-red-700 text-white px-4 py-2 rounded-full hover:bg-red-800 transition duration-300 font-semibold">Sign In</a>`;

        document.getElementById('userContainer').innerHTML = content;
    }
	
	

    // Toggle the visibility of the popup
    function togglePopup() {
        let popup = document.getElementById('userPopup');
        if (popup) {
            popup.classList.toggle('hidden');
            document.getElementById('popupUserName').textContent = localStorage.getItem('name') || 'User';
        }
    }

    // Handle clicks on the page to show/hide the popup
    document.addEventListener('click', function(event) {
        let userContainer = document.getElementById('userContainer');
        let popup = document.getElementById('userPopup');

        if (userContainer && userContainer.contains(event.target)) {
            togglePopup();
        } else if (popup && !popup.classList.contains('hidden')) {
            popup.classList.add('hidden');
        }
    });

    // Call the function to display the user info or Sign In button
    displayUserOrSignIn();
});

// Update the footer inner HTML
const footerInnerHtml = `
    <div>
        <button class="bg-red-700 text-white py-3 px-6 rounded-full w-full">Register for newsletter</button>
    </div>
    <div class="flex space-x-10 w-full items-start justify-around">
        <div class="w-1/3 flex flex-col space-y-4">
            <h4 class="flex font-bold border-b-2 pb-4">Legal information</h4>
            <a href="#" class="border-b-2 pb-1">Terms of use</a>
            <a href="#" class="border-b-2 pb-1">Data protection</a>
            <a href="#" class="border-b-2 pb-1">Cookies</a>
            <a href="#" class="border-b-2 pb-1">Imprint</a>
        </div>
        <div class="w-1/3 flex flex-col space-y-4">
            <h4 class="flex font-bold border-b-2 pb-4">More Pages</h4>
            <a href="#" class="border-b-2 pb-1">STnet.ch - B2B Portal</a>
            <a href="#" class="border-b-2 pb-1">Jobs</a>
            <a href="#" class="border-b-2 pb-1">Partners</a>
            <a href="#" class="border-b-2 pb-1">Apps</a>
            <a href="#" class="border-b-2 pb-1">Annual report</a>
        </div>
        <div class="w-1/3 flex flex-col space-y-4">
            <h4 class="flex font-bold border-b-2 pb-4">Contact</h4>
            <a href="#" class="border-b-2 pb-1">Address</a>
            <a href="#" class="border-b-2 pb-1">Help center</a>
            <a href="#" class="border-b-2 pb-1">Media Corner</a>
            <a href="#" class="border-b-2 pb-1">Trade Corner</a>
        </div>
    </div>
    <div class="flex space-x-10 w-full items-start justify-start">
        <div class="w-1/3 flex flex-col space-y-4">
            <h4>Newsletter</h4>
            <button class="bg-red-700 text-white py-3 px-6 rounded-full">Register for newsletter</button>
        </div>
        <div>
            <a href="#">© 2024 Mergui Guide</a>
        </div>
    </div>
`;

// Update footer container inner HTML
const footerContainer = document.getElementById("footerContainer");
if (footerContainer) {
    footerContainer.innerHTML = footerInnerHtml;
}
