<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet" />
    <style>
        .hidden {
            display: none;
        }
        .card {
            background-color: #f9fafb;
            border-radius: 0.5rem;
            padding: 1.5rem;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
            text-align: center;
            height: auto;
        }
        .table-container {
            margin-top: 1rem;
            overflow-x: auto;
        }
        .dropdown {
            position: absolute;
            top: 100%;
            right: 10;
            background-color: white;
            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.2);
            border-radius: 0.5rem;
            padding: 1rem;
            z-index: 20;
        }
        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            backdrop-filter: blur(5px);
            z-index: 10;
        }
        

header {
  color: #b8162a;
  margin-bottom: 20px;
  font-size: 18px;
  font-weight: 600;
  text-align: center;
}
.input_field {
  position: relative;
  height: 45px;
  margin-top: 15px;
  width: 100%;
}
.refresh_button {
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
  background: #b8162a;
  height: 30px;
  width: 30px;
  border: none;
  border-radius: 4px;
  color: #fff;
  cursor: pointer;
}
.refresh_button:active {
  transform: translateY(-50%) scale(0.98);
}
.input_field input,
.button button {
  height: 100%;
  width: 100%;
  outline: none;
  border: none;
  border-radius: 8px;
}
.input_field input {
  padding: 0 15px;
  border: 1px solid rgba(0, 0, 0, 0.1);
}
.captch_box input {
  color: #b8162a;
  font-size: 22px;
  pointer-events: none;
}
.captch_input input:focus {
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.08);
}
.message {
  font-size: 14px;
  margin: 14px 0;
  color: #826afb;
  display: none;
}
.message.active {
  display: block;
}
.button button {
  background: #b8162a;
  color: #fff;
  cursor: pointer;
  user-select: none;
}
.button button:active {
  transform: scale(0.99);
}
.button.disabled {
  opacity: 0.6;
  pointer-events: none;
}
    </style>
</head>
<body class="flex h-screen bg-gray-100">
    <!-- Sidebar -->
    <aside class="w-64 bg-red-700 text-white flex-shrink-0">
        <div class="p-4">
            <h1 class="text-2xl font-bold">Admin Dashboard</h1>
            <nav class="mt-6">
                <ul>
                    <li><a href="#" class="block py-2 px-4 hover:bg-red-600 rounded" onclick="showSection('partners')">Partner Management</a></li>
                    <li><a href="#" class="block py-2 px-4 hover:bg-red-600 rounded" onclick="showSection('articles')">Article Management</a></li>
                </ul>
            </nav>
        </div>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-6 overflow-y-auto relative">
        <!-- Partner Management Section -->
        <section id="partners" class="bg-white p-6 relative rounded-lg shadow-md hidden">
            <header class="flex items-center justify-between mb-6 relative">
                <h1 class="text-3xl font-semibold text-red-700">Partner Management</h1>
                <button class="bg-red-700 text-white px-4 py-2 rounded hover:bg-red-600" onclick="toggleDropdown()">Send Activation Code</button>
                <!-- Overlay -->
                <div id="overlay" class="overlay hidden"></div>
                <!-- Dropdown Content -->
                <div id="dropdown" class="dropdown absolute top-20 backdrop-opacity-5 right-20 hidden">
  					 <div class="container">
	      				<header class="text-red-800">Generate Activation Code</header>
					      <div class="input_field captch_box">
					        <input type="text" value="" disabled />
				<button class="refresh_button" aria-label="Refresh">
    <svg height="30px" width="30px" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="-49.62 -49.62 595.41 595.41" xml:space="preserve" fill="#b8162a" stroke="#b8162a"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path style="fill:#ffffffff;" d="M0.005,248.087C0.005,111.063,111.073,0,248.079,0c137.014,0,248.082,111.062,248.082,248.087 c0,137.002-111.068,248.079-248.082,248.079C111.073,496.166,0.005,385.089,0.005,248.087z"></path> <path style="fill:#ffffffffF7F7F7;" d="M400.813,169.581c-2.502-4.865-14.695-16.012-35.262-5.891 c-20.564,10.122-10.625,32.351-10.625,32.351c7.666,15.722,11.98,33.371,11.98,52.046c0,65.622-53.201,118.824-118.828,118.824 c-65.619,0-118.82-53.202-118.82-118.824c0-61.422,46.6-111.946,106.357-118.173v30.793c0,0-0.084,1.836,1.828,2.999 c1.906,1.163,3.818,0,3.818,0l98.576-58.083c0,0,2.211-1.162,2.211-3.436c0-1.873-2.211-3.205-2.211-3.205l-98.248-57.754 c0,0-2.24-1.605-4.23-0.826c-1.988,0.773-1.744,3.481-1.744,3.481v32.993c-88.998,6.392-159.23,80.563-159.23,171.21 c0,94.824,76.873,171.696,171.693,171.696c94.828,0,171.707-76.872,171.707-171.696 C419.786,219.788,412.933,193.106,400.813,169.581z"></path> </g></svg>
</button>

					      </div>
					      <div class="input_field captch_input">
					        <input type="text" placeholder="Add Description" />
					      </div>
					      <div class="message">Entered captcha is correct</div>
					      <div class="input_field button disabled text-red-700">
					        <button>Add to Database</button>
					      </div>
			    	</div>
                </div>
            </header>
            <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-6">
                <!-- Hotels Box -->
                <div class="card">
                    <h2 class="text-xl font-semibold mb-4">Hotels</h2>
                    <p class="text-3xl font-bold text-red-700">25</p>
                    <form action="search_hotels.jsp" method="get" class="mt-4">
                        <input type="text" name="query" placeholder="Search Hotels" class="w-full p-2 mb-4 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-500">
                        <button type="submit" class="bg-red-700 text-white px-4 py-2 rounded hover:bg-red-600">Search</button>
                    </form>
                    <div class="table-container mt-4">
                        <table class="w-full bg-white border border-gray-300 mt-4">
                            <thead>
                                <tr class="bg-gray-200">
                                    <th class="p-2 border-b">Hotel Name</th>
                                    <th class="p-2 border-b">Location</th>
                                    <th class="p-2 border-b">Rating</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="p-2 border-b">Grand Hotel</td>
                                    <td class="p-2 border-b">City Center</td>
                                    <td class="p-2 border-b">4.5</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Restaurants Box -->
                <div class="card">
                    <h2 class="text-xl font-semibold mb-4">Restaurants</h2>
                    <p class="text-3xl font-bold text-red-700">15</p>
                    <form action="search_restaurants.jsp" method="get" class="mt-4">
                        <input type="text" name="query" placeholder="Search Restaurants" class="w-full p-2 mb-4 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-500">
                        <button type="submit" class="bg-red-700 text-white px-4 py-2 rounded hover:bg-red-600">Search</button>
                    </form>
                    <div class="table-container mt-4">
                        <table class="w-full bg-white border border-gray-300 mt-4">
                            <thead>
                                <tr class="bg-gray-200">
                                    <th class="p-2 border-b">Restaurant Name</th>
                                    <th class="p-2 border-b">Cuisine</th>
                                    <th class="p-2 border-b">Rating</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="p-2 border-b">Cafe Gourmet</td>
                                    <td class="p-2 border-b">Italian</td>
                                    <td class="p-2 border-b">4.2</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Transportation Box -->
                <div class="card">
                    <h2 class="text-xl font-semibold mb-4">Transportation</h2>
                    <p class="text-3xl font-bold text-red-700">8</p>
                    <form action="search_transportation.jsp" method="get" class="mt-4">
                        <input type="text" name="query" placeholder="Search Transportation" class="w-full p-2 mb-4 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-500">
                        <button type="submit" class="bg-red-700 text-white px-4 py-2 rounded hover:bg-red-600">Search</button>
                    </form>
                    <div class="table-container mt-4">
                        <table class="w-full bg-white border border-gray-300 mt-4">
                            <thead>
                                <tr class="bg-gray-200">
                                    <th class="p-2 border-b">Transportation Type</th>
                                    <th class="p-2 border-b">Company</th>
                                    <th class="p-2 border-b">Rating</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="p-2 border-b">Taxi</td>
                                    <td class="p-2 border-b">Fast Taxi Co.</td>
                                    <td class="p-2 border-b">4.0</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>

        <!-- Article Management Section -->
        <section id="articles" class="bg-white p-6 rounded-lg shadow-md hidden">
            <form action="search_articles.jsp" method="get">
                <input type="text" name="query" placeholder="Search Articles" class="w-full p-2 mb-4 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-500">
                <button type="submit" class="bg-red-700 text-white px-4 py-2 rounded hover:bg-red-600">Search</button>
            </form>
            <table class="w-full bg-white border border-gray-300 mt-4">
                <thead>
                    <tr class="bg-gray-200">
                        <th class="p-2 border-b">Title</th>
                        <th class="p-2 border-b">Date</th>
                        <th class="p-2 border-b">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="p-2 border-b">Discover the City</td>
                        <td class="p-2 border-b">2024-08-21</td>
                        <td class="p-2 border-b">
                            <a href="edit_article.jsp" class="bg-black text-white px-2 py-1 rounded hover:bg-yellow-600">Edit</a>
                            <a href="delete_article.jsp" class="bg-red-700 text-white px-2 py-1 rounded hover:bg-red-600">Delete</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </section>
    </main>

    <script>
        function showSection(sectionId) {
            const sections = document.querySelectorAll('main > section');
            sections.forEach(section => {
                section.classList.toggle('hidden', section.id !== sectionId);
            });
        }

        function toggleDropdown() {
            const dropdown = document.getElementById('dropdown');
            const overlay = document.getElementById('overlay');
            dropdown.classList.toggle('hidden');
            overlay.classList.toggle('hidden');
        }

        document.addEventListener('click', function (event) {
            const dropdown = document.getElementById('dropdown');
            const overlay = document.getElementById('overlay');
            const button = document.querySelector('button[onclick="toggleDropdown()"]');
            if (!dropdown.contains(event.target) && event.target !== button) {
                dropdown.classList.add('hidden');
                overlay.classList.add('hidden');
            }
        });

        document.addEventListener('DOMContentLoaded', () => {
            showSection('partners');
        });
        
        //Show Code Generator
        
        // Selecting necessary DOM elements
		const captchaTextBox = document.querySelector(".captch_box input");
		const refreshButton = document.querySelector(".refresh_button");
		const captchaInputBox = document.querySelector(".captch_input input");
		const message = document.querySelector(".message");
		const submitButton = document.querySelector(".button");
		
		// Variable to store generated captcha
		let captchaText = null;
		
		// Function to generate captcha
		const generateCaptcha = () => {
		  const randomString = Math.random().toString(36).substring(2, 7);
		  const randomStringArray = randomString.split("");
		  const changeString = randomStringArray.map((char) => (Math.random() > 0.5 ? char.toUpperCase() : char));
		  captchaText = changeString.join("   ");
		  captchaTextBox.value = captchaText;
		  console.log(captchaText);
		};
		
		const refreshBtnClick = () => {
		  generateCaptcha();
		  captchaInputBox.value = "";
		  captchaKeyUpValidate();
		};
		
		const captchaKeyUpValidate = () => {
		  //Toggle submit button disable class based on captcha input field.
		  submitButton.classList.toggle("disabled", !captchaInputBox.value);
		
		  if (!captchaInputBox.value) message.classList.remove("active");
		};
		
		// Function to validate the entered captcha
		const submitBtnClick = () => {
		  captchaText = captchaText
		    .split("")
		    .filter((char) => char !== " ")
		    .join("");
		  message.classList.add("active");
		  // Check if the entered captcha text is correct or not
		  if (captchaInputBox.value === captchaText) {
		    message.innerText = "Entered captcha is correct";
		    message.style.color = "#b8162a";
		  } else {
		    message.innerText = "Entered captcha is not correct";
		    message.style.color = "#FF2525";
		  }
		};
		
		// Add event listeners for the refresh button, captchaInputBox, submit button
		refreshButton.addEventListener("click", refreshBtnClick);
		captchaInputBox.addEventListener("keyup", captchaKeyUpValidate);
		submitButton.addEventListener("click", submitBtnClick);
		
		// Generate a captcha when the page loads
		generateCaptcha();
    </script>
</body>
</html>
