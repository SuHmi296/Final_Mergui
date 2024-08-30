<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"
	crossorigin="anonymous"></script>
<script src="logout.js"></script>

</head>

<body class="bg-gray-100 font-sans">
	<!-- Navbar -->
	<nav id="navContainer"
		class="z-50 flex justify-between items-center p-1 px-20 sticky top-0 bg-white backdrop-blur-md"></nav>

	<!-- Hero Section -->
	<div class="h-screen bg-cover bg-center"
		style="background-image: url('./Images/backgroundimg.jpg')">
		&nbsp;</div>


	<!-- Secondary Navbar for Bookings -->
	<div class="my-4 flex justify-evenly">
		<button id="bookingBtn"
			class="bg-red-700 text-white py-2 px-6 rounded hover:bg-red-500 transition">
			Hotel Booking</button>
		<button id="bookingRes"
			class="bg-red-700 text-white py-2 px-6 rounded hover:bg-red-500 transition">
			Restaurant Booking</button>
		<button id="bookingTa"
			class="bg-red-700 text-white py-2 px-6 rounded hover:bg-red-500 transition">
			Taxi Booking</button>
	</div>

	<!-- Hotel Booking Form -->
	<!-- Hotel Booking Section -->
	<div
		class="max-w-7xl mx-auto p-6 flex flex-col md:flex-row justify-between items-center space-y-6 md:space-y-0 md:space-x-8">

		<!-- Booking Form -->
		<div id="bookingForm"
			class="bg-white p-6 rounded-lg shadow-md w-full md:w-1/2">
			<form class="space-y-4">
				<!-- Hotel Name -->
				<div>
					<label for="hotelName" class="block font-semibold text-gray-800">Hotel
						Name:</label> <input type="text" id="hotelName" name="hotelName"
						class="w-full p-2 border border-gray-300 rounded mt-1"
						placeholder="Enter hotel name" required />
				</div>

				<!-- Arrival and Departure Dates -->
				<div class="grid grid-cols-2 gap-4">
					<div>
						<label for="arrivalDate" class="block font-semibold text-gray-800">Arrival
							Date (optional):</label> <input type="date" id="arrivalDate"
							name="arrivalDate"
							class="w-full p-2 border border-gray-300 rounded mt-1" />
					</div>
					<div>
						<label for="departureDate"
							class="block font-semibold text-gray-800">Departure Date
							(optional):</label> <input type="date" id="departureDate"
							name="departureDate"
							class="w-full p-2 border border-gray-300 rounded mt-1" />
					</div>
				</div>

				<!-- Arrival Time -->
				<div>
					<label for="arrivalTime" class="block font-semibold text-gray-800">Arrival
						Time:</label> <input type="time" id="arrivalTime" name="arrivalTime"
						class="w-full p-2 border border-gray-300 rounded mt-1" />
				</div>

				<!-- Phone Number -->
				<div>
					<label for="phone" class="block font-semibold text-gray-800">Phone
						no:</label> <input type="text" id="phone" name="phone"
						class="w-full p-2 border border-gray-300 rounded mt-1"
						placeholder="Enter phone number" required />
				</div>

				<!-- Room Selection -->
				<div>
					<label for="room" class="block font-semibold text-gray-800">Room:</label>
					<select id="room" name="room"
						class="w-full p-2 border border-gray-300 rounded mt-1">
						<option value="1">1 Room</option>
						<option value="2">2 Rooms</option>
						<option value="3">3 Rooms</option>
						<option value="4">4 Rooms</option>
						<option value="5">5 Rooms</option>
					</select>
				</div>

				<!-- Guests Selection -->
				<div class="relative">
					<label for="guests" class="block font-semibold text-gray-800">Guests:</label>
					<div id="guests"
						class="bg-gray-700 text-white p-2 rounded mt-1 cursor-pointer text-center">
						1 Adult, 0 Children</div>
					<div
						class="absolute bg-gray-800 text-white mt-1 p-4 rounded-lg shadow-lg hidden">
						<div class="flex justify-between items-center mb-2">
							<span>Adults</span>
							<div class="flex items-center space-x-2">
								<button type="button" class="bg-gray-700 text-white p-1 rounded">
									-</button>
								<span id="adultsCount" class="text-white">1</span>
								<button type="button" class="bg-gray-700 text-white p-1 rounded">
									+</button>
							</div>
						</div>
						<div class="flex justify-between items-center">
							<span>Children</span>
							<div class="flex items-center space-x-2">
								<button type="button" class="bg-gray-600 text-white p-1 rounded">
									-</button>
								<span id="childrenCount" class="text-white">0</span>
								<button type="button" class="bg-gray-600 text-white p-1 rounded">
									+</button>
							</div>
						</div>
					</div>
				</div>

				<!-- Submit Button -->
				<button type="submit"
					class="w-full bg-red-700 text-white p-2 rounded mt-4 hover:bg-red-500 transition">
					Submit Booking</button>
			</form>
		</div>

		<!-- Photo and Content Section -->
		<div
			class="bg-white p-6 rounded-lg shadow-md w-full md:w-1/2 flex flex-col">
			<!-- Photo -->
			<img src="./Images/edp.jpg" alt="Hotel Photo"
				class="w-full h-64 object-cover rounded-lg mb-6">

			<!-- Content -->
			<div>
				<h2 class="text-2xl font-semibold text-gray-800 mb-4">Experience
					Luxury at Our Hotel</h2>
				<p class="text-gray-600">Indulge in a unique blend of luxury and
					comfort at our hotel. Enjoy world-class amenities, exceptional
					service, and a serene environment. Perfect for both leisure and
					business stays, our hotel offers a variety of rooms and suites
					designed to cater to your every need.</p>
			</div>

			<!-- Horizontal Scrollable Image Section -->
			<div
				class="flex overflow-x-auto space-x-4 scrollbar-thin scrollbar-thumb-red-700 scrollbar-track-gray-300 mt-6">
				<img src="./Images/edp.jpg" alt="Food Image 1"
					class="w-64 h-48 object-cover rounded"> <img
					src="./Images/edp.jpg" alt="Food Image 2"
					class="w-64 h-48 object-cover rounded"> <img
					src="./Images/edp.jpg" alt="Food Image 3"
					class="w-64 h-48 object-cover rounded"> <img
					src="./Images/edp.jpg" alt="Food Image 4"
					class="w-64 h-48 object-cover rounded"> <img
					src="./Images/edp.jpg" alt="Food Image 5"
					class="w-64 h-48 object-cover rounded">
			</div>
		</div>

	</div>

	<!-- Restaurant Booking -->
	<div
		class="flex justify-between items-center md:w-1/3 max-w-7xl mx-auto p-6">
		<div id="bookingRestaurant"
			class="p-6 bg-white shadow-lg rounded-lg max-w-lg mx-auto mt-8">

			<form class="space-y-4">
				<div>
					<label for="restaurantName" class="block font-semibold">Restaurant
						Name:</label> <input type="text" id="restaurantName" name="restaurantName"
						placeholder="Enter restaurant name" required
						class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-red-500 transition duration-300" />
				</div>
				<!-- Event Type Dropdown -->
				<div>
					<label for="eventType" class="block font-semibold">Event
						Type:</label> <select id="eventType" name="eventType"
						class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-red-500 transition duration-300">
						<option value="birthday">Birthday</option>
						<option value="anniversary">Anniversary</option>
						<option value="business">Business Meeting</option>
						<option value="wedding">Wedding</option>
						<option value="other">Other</option>
					</select>
				</div>

				<div class="grid grid-cols-2 gap-4">
					<div>
						<label for="arrivalDate" class="block font-semibold">Event
							Date (optional):</label> <input type="date" id="arrivalDate"
							name="arrivalDate"
							class="w-full p-2 border border-gray-300 rounded mt-1" />
					</div>
					<div>
						<label for="arrivalTime" class="block font-semibold">Event
							Time:</label> <input type="time" id="arrivalTime" name="arrivalTime"
							class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-red-500 transition duration-300" />
					</div>
				</div>

				<div>
					<label for="phone" class="block font-semibold">Phone no:</label> <input
						type="text" id="phone" name="phone" placeholder="Enter phone no"
						required
						class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-red-500 transition duration-300" />
				</div>
				<div class="grid grid-cols-2 gap-4">
					<div>
						<label for="guests" class="block font-semibold">Guests:</label> <input
							type="text" id="guests" name="guests"
							placeholder="Enter number of guests" required
							class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-red-500 transition duration-300" />
					</div>
					<div>
						<label for="tables" class="block font-semibold">Number of
							Tables:</label> <select id="tables" name="tables"
							class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-red-500 transition duration-300">
							<option value="1">1 Table</option>
							<option value="2">2 Tables</option>
							<option value="3">3 Tables</option>
							<option value="4">4 Tables</option>
							<option value="5">5 Tables</option>
						</select>
					</div>
				</div>
				<div>
					<button type="submit"
						class="w-full bg-red-700 text-white p-2 rounded mt-4 hover:bg-red-500 transition">Submit
						Booking</button>
				</div>
			</form>
		</div>
	</div>


	<!-- Taxi Booking -->
	<div class="flex justify-between items-center max-w-7xl mx-auto p-6">
		<div id="bookingTaxi"
			class="bg-white p-6 rounded-lg shadow-lg max-w-md mx-auto">
			<form class="space-y-4">
				<div>
					<label for="taxiNo" class="block font-semibold">Taxi No:</label> <input
						type="text" id="taxiName" name="taxiName"
						placeholder="Enter taxi name" required
						class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-300" />
				</div>
				<div>
					<label for="taxiName" class="block font-semibold">Driver
						Name:</label> <input type="text" id="taxiName" name="taxiName"
						placeholder="Enter taxi name" required
						class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-300" />
				</div>
				<div class="grid grid-cols-2 gap-4">
					<div>
						<label for="arrivalDate" class="block font-semibold">Order
							Date (optional):</label> <input type="date" id="arrivalDate"
							name="arrivalDate"
							class="w-full p-2 border border-gray-300 rounded mt-1" />
					</div>
					<div>
						<label for="arrivalTime" class="block font-semibold">Order
							Time:</label> <input type="time" id="arrivalTime" name="arrivalTime"
							class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-red-500 transition duration-300" />
					</div>
				</div>
				<div>
					<label for="phone" class="block font-semibold">Phone no:</label> <input
						type="text" id="phone" name="phone" placeholder="Enter phone no"
						required
						class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-300" />
				</div>
				<div>
					<label for="guests" class="block font-semibold">Number of
						Guests:</label> <input type="text" id="guests" name="guests"
						placeholder="Enter number of guests" required
						class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-300" />
				</div>
				<div class="grid grid-cols-2 gap-4">
					<div>
						<label for="taxi" class="block font-semibold">Taxi type:</label> <select
							id="taxi" name="taxi"
							class="w-full p-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-300">
							<option value="1">Motorcycle</option>
							<option value="2">A tricycle</option>
							<option value="3">Mini Truck</option>
						</select>
					</div>
					<div>
						<label for="room" class="block font-semibold">Taxi:</label> <select
							id="room" name="room"
							class="w-full p-2 border border-gray-300 rounded mt-1">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</div>
				</div>
				<div>
					<button type="submit"
						class="w-full bg-red-700 text-white p-2 rounded mt-4 hover:bg-red-500 transition">Find
						Booking</button>
				</div>
			</form>
		</div>
	</div>


	<div class="max-w-7xl mx-auto  mb-6">
    <div class="relative rounded-lg overflow-hidden shadow-lg">
        <img
            src="./Images/att.jpg"
            class="w-full h-[500px] object-cover transition-transform duration-300 ease-in-out transform hover:scale-105"
        />
        <div
            class="absolute inset-0 bg-gradient-to-t from-black via-transparent to-transparent opacity-75"
        ></div>
        <div class="absolute bottom-6 left-6 p-6 space-y-4 bg-opacity-75 backdrop-blur-md border border-white border-opacity-50 rounded-lg">
    <p class="text-sm uppercase tracking-wider text-white">Top Attractions</p>
    <h1 class="text-4xl text-red-700 font-bold">Famous Islands</h1>
    <p class="text-sm text-white">
        Experience the roar and vibration of the mighty Rhine Falls, Europe's largest waterfall, and explore castles, the Rhine Falls Basin, and more by ship.
    </p>
</div>

    </div>
</div>


	<div class="container max-w-7xl mx-auto p-6">
		<!-- Flexbox Container -->
		<div class="flex flex-wrap items-center flex-row">
			<!-- Text Section -->
			<div class="w-full md:w-1/2 mb-4 md:mb-0">
				<h1 class="text-6xl font-bold text-gray-900">Discover</h1>
				<h1 class="text-6xl font-bold text-gray-900">Switzerland now!</h1>
			</div>

			<!-- Image Section -->
			<div class="w-full md:w-1/2">
				<div class="relative">
					<img
						src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_620,h_300/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/global/stage%20header%20history%20of%20switzerland/image%20all%20season/59191_32001800.jpeg"
						class="rounded-lg w-full h-auto object-cover brightness-75" />
					<div
						class="absolute bottom-10 left-8 transition-1.5s w-auto z-10 text-white brightness-125 text-3xl font-bold shadow-lg">
						Sustainability</div>
				</div>
			</div>
		</div>
	</div>

	<div class="max-w-7xl mx-auto p-6">
		<h1 class="text-3xl font-bold mb-6">Expert tips</h1>

		<div class="flex flex-wrap -mx-4">
			<!-- Tip 1 -->
			<div class="w-full sm:w-1/2 lg:w-1/3 p-4 group">
				<div class="bg-transparent rounded-lg overflow-hidden">
					<div
						class="w-[360px] h-52 flex justify-center items-center rounded-xl">
						<div
							class="w-full h-full rounded-lg transition-all overflow-hidden duration-300 group-hover:scale-95">
							<img
								class="w-full h-full rounded-lg object-cover transition-all duration-300 group-hover:scale-125"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Waterside hikes" />
						</div>
					</div>
					<div class="p-4">
						<h2 class="text-xl font-bold mb-2">Waterside hikes</h2>
						<p class="text-gray-600 mb-4">What could be lovelier than a
							hike to a lake, along a river or even near a glacier? Water is a
							constant companion on these nine hikes. Immerse yourself.</p>
						<a href="#" class="text-red-600 font-bold hover:underline">Learn
							more &rarr;</a>
					</div>
				</div>
			</div>

			<div class="w-full sm:w-1/2 lg:w-1/3 p-4 group">
				<div class="bg-transparent rounded-lg overflow-hidden">
					<div
						class="w-[360px] h-52 flex justify-center items-center rounded-xl">
						<div
							class="w-full h-full rounded-lg transition-all overflow-hidden duration-300 group-hover:scale-95">
							<img
								class="w-full h-full rounded-lg object-cover transition-all duration-300 group-hover:scale-125"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Waterside hikes" />
						</div>
					</div>
					<div class="p-4">
						<h2 class="text-xl font-bold mb-2">Waterside hikes</h2>
						<p class="text-gray-600 mb-4">What could be lovelier than a
							hike to a lake, along a river or even near a glacier? Water is a
							constant companion on these nine hikes. Immerse yourself.</p>
						<a href="#" class="text-red-600 font-bold hover:underline">Learn
							more &rarr;</a>
					</div>
				</div>
			</div>

			<!-- Tip 2 -->
			<div class="w-full sm:w-1/2 lg:w-1/3 p-4 group">
				<div class="bg-transparent rounded-lg overflow-hidden">
					<div
						class="w-[360px] h-52 flex justify-center items-center rounded-xl">
						<div
							class="w-full h-full rounded-lg transition-all overflow-hidden duration-300 group-hover:scale-95">
							<img
								class="w-full h-full rounded-lg object-cover transition-all duration-300 group-hover:scale-125"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Waterside hikes" />
						</div>
					</div>
					<div class="p-4">
						<h2 class="text-xl font-bold mb-2">Waterside hikes</h2>
						<p class="text-gray-600 mb-4">What could be lovelier than a
							hike to a lake, along a river or even near a glacier? Water is a
							constant companion on these nine hikes. Immerse yourself.</p>
						<a href="#" class="text-red-600 font-bold hover:underline">Learn
							more &rarr;</a>
					</div>
				</div>
			</div>

			<!-- Tip 3 -->
			<div class="w-full sm:w-1/2 lg:w-1/3 p-4 group">
				<div class="bg-transparent rounded-lg overflow-hidden">
					<div
						class="w-[360px] h-52 flex justify-center items-center rounded-xl">
						<div
							class="w-full h-full rounded-lg transition-all overflow-hidden duration-300 group-hover:scale-95">
							<img
								class="w-full h-full rounded-lg object-cover transition-all duration-300 group-hover:scale-125"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Waterside hikes" />
						</div>
					</div>
					<div class="p-4">
						<h2 class="text-xl font-bold mb-2">Waterside hikes</h2>
						<p class="text-gray-600 mb-4">What could be lovelier than a
							hike to a lake, along a river or even near a glacier? Water is a
							constant companion on these nine hikes. Immerse yourself.</p>
						<a href="#" class="text-red-600 font-bold hover:underline">Learn
							more &rarr;</a>
					</div>
				</div>
			</div>

			<div class="w-full sm:w-1/2 lg:w-1/3 p-4 group">
				<div class="bg-transparent rounded-lg overflow-hidden">
					<div
						class="w-[360px] h-52 flex justify-center items-center rounded-xl">
						<div
							class="w-full h-full rounded-lg transition-all overflow-hidden duration-300 group-hover:scale-95">
							<img
								class="w-full h-full rounded-lg object-cover transition-all duration-300 group-hover:scale-125"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Waterside hikes" />
						</div>
					</div>
					<div class="p-4">
						<h2 class="text-xl font-bold mb-2">Waterside hikes</h2>
						<p class="text-gray-600 mb-4">What could be lovelier than a
							hike to a lake, along a river or even near a glacier? Water is a
							constant companion on these nine hikes. Immerse yourself.</p>
						<a href="#" class="text-red-600 font-bold hover:underline">Learn
							more &rarr;</a>
					</div>
				</div>

				<!-- Additional tips can be added similarly -->
			</div>
		</div>

		<!--Promotion Session-->
		<div class="max-w-7xl p-6 flex flex-wrap -mx-4">
			<h1 class="text-3xl font-bold mb-6">Promotions</h1>

			<div class="flex flex-wrap -mx-4">
				<!-- Event Card 1 -->
				<div class="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 p-4 group">
					<div
						class="bg-transparentoverflow-hidden transition-all duration-300">
						<div
							class="relative rounded-lg group-hover:scale-95 transition-all duration-300 overflow-hidden">
							<img
								class="w-full h-40 object-cover group-hover:scale-125 transition-all duration-300"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Event 1" />
							<div
								class="absolute bg-white p-2 rounded flex flex-row bottom-4 space-x-2 left-4">
								<span class="block text-sm text-gray-700 mb-2">Till</span>
								<div class="flex flex-col">
									<p class="text-2xl font-bold">13</p>
									<strong>Aug</strong>
								</div>
							</div>
						</div>
						<div class="p-4">
							<h2 class="text-lg font-bold">Lucerne Festival in Summer</h2>
							<p class="text-gray-600">Lucerne, 13.08. - 15.09.2024
								sdfghjksdfghjkl</p>
						</div>
					</div>
				</div>

				<!-- Event Card 2 -->
				<div class="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 p-4 group">
					<div
						class="bg-transparentoverflow-hidden transition-all duration-300">
						<div
							class="relative rounded-lg group-hover:scale-95 transition-all duration-300 overflow-hidden">
							<img
								class="w-full h-40 object-cover group-hover:scale-125 transition-all duration-300"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Event 1" />
							<div
								class="absolute bg-white p-2 rounded flex flex-row bottom-4 space-x-2 left-4">
								<span class="block text-sm text-gray-700 mb-2">Till</span>
								<div class="flex flex-col">
									<p class="text-2xl font-bold">13</p>
									<strong>Aug</strong>
								</div>
							</div>
						</div>
						<div class="p-4">
							<h2 class="text-lg font-bold">Lucerne Festival in Summer</h2>
							<p class="text-gray-600">Lucerne, 13.08. - 15.09.2024
								sdfghjksdfghjkl</p>
						</div>
					</div>
				</div>

				<!-- Event Card 3 -->
				<div class="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 p-4 group">
					<div
						class="bg-transparentoverflow-hidden transition-all duration-300">
						<div
							class="relative rounded-lg group-hover:scale-95 transition-all duration-300 overflow-hidden">
							<img
								class="w-full h-40 object-cover group-hover:scale-125 transition-all duration-300"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Event 1" />
							<div
								class="absolute bg-white p-2 rounded flex flex-row bottom-4 space-x-2 left-4">
								<span class="block text-sm text-gray-700 mb-2">Till</span>
								<div class="flex flex-col">
									<p class="text-2xl font-bold">13</p>
									<strong>Aug</strong>
								</div>
							</div>
						</div>
						<div class="p-4">
							<h2 class="text-lg font-bold">Lucerne Festival in Summer</h2>
							<p class="text-gray-600">Lucerne, 13.08. - 15.09.2024
								sdfghjksdfghjkl</p>
						</div>
					</div>
				</div>

				<!-- Event Card 4 -->
				<div class="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 p-4 group">
					<div
						class="bg-transparentoverflow-hidden transition-all duration-300">
						<div
							class="relative rounded-lg group-hover:scale-95 transition-all duration-300 overflow-hidden">
							<img
								class="w-full h-40 object-cover group-hover:scale-125 transition-all duration-300"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Event 1" />
							<div
								class="absolute bg-white p-2 rounded flex flex-row bottom-4 space-x-2 left-4">
								<span class="block text-sm text-gray-700 mb-2">Till</span>
								<div class="flex flex-col">
									<p class="text-2xl font-bold">13</p>
									<strong>Aug</strong>
								</div>
							</div>
						</div>
						<div class="p-4">
							<h2 class="text-lg font-bold">Lucerne Festival in Summer</h2>
							<p class="text-gray-600">Lucerne, 13.08. - 15.09.2024
								sdfghjksdfghjkl</p>
						</div>
					</div>
				</div>

				<!-- Event Card 4 -->
				<div class="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 p-4 group">
					<div
						class="bg-transparentoverflow-hidden transition-all duration-300">
						<div
							class="relative rounded-lg group-hover:scale-95 transition-all duration-300 overflow-hidden">
							<img
								class="w-full h-40 object-cover group-hover:scale-125 transition-all duration-300"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Event 1" />
							<div
								class="absolute bg-white p-2 rounded flex flex-row bottom-4 space-x-2 left-4">
								<span class="block text-sm text-gray-700 mb-2">Till</span>
								<div class="flex flex-col">
									<p class="text-2xl font-bold">13</p>
									<strong>Aug</strong>
								</div>
							</div>
						</div>
						<div class="p-4">
							<h2 class="text-lg font-bold">Lucerne Festival in Summer</h2>
							<p class="text-gray-600">Lucerne, 13.08. - 15.09.2024
								sdfghjksdfghjkl</p>
						</div>
					</div>
				</div>

				<div class="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 p-4 group">
					<div
						class="bg-transparentoverflow-hidden transition-all duration-300">
						<div
							class="relative rounded-lg group-hover:scale-95 transition-all duration-300 overflow-hidden">
							<img
								class="w-full h-40 object-cover group-hover:scale-125 transition-all duration-300"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Event 1" />
							<div
								class="absolute bg-white p-2 rounded flex flex-row bottom-4 space-x-2 left-4">
								<span class="block text-sm text-gray-700 mb-2">Till</span>
								<div class="flex flex-col">
									<p class="text-2xl font-bold">13</p>
									<strong>Aug</strong>
								</div>
							</div>
						</div>
						<div class="p-4">
							<h2 class="text-lg font-bold">Lucerne Festival in Summer</h2>
							<p class="text-gray-600">Lucerne, 13.08. - 15.09.2024
								sdfghjksdfghjkl</p>
						</div>
					</div>
				</div>

				<!-- Event Card 4 -->
				<div class="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 p-4 group">
					<div
						class="bg-transparentoverflow-hidden transition-all duration-300">
						<div
							class="relative rounded-lg group-hover:scale-95 transition-all duration-300 overflow-hidden">
							<img
								class="w-full h-40 object-cover group-hover:scale-125 transition-all duration-300"
								src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
								alt="Event 1" />
							<div
								class="absolute bg-white p-2 rounded flex flex-row bottom-4 space-x-2 left-4">
								<span class="block text-sm text-gray-700 mb-2">Till</span>
								<div class="flex flex-col">
									<p class="text-2xl font-bold">13</p>
									<strong>Aug</strong>
								</div>
							</div>
						</div>
						<div class="p-4">
							<h2 class="text-lg font-bold">Lucerne Festival in Summer</h2>
							<p class="text-gray-600">Lucerne, 13.08. - 15.09.2024
								sdfghjksdfghjkl</p>
						</div>
					</div>
				</div>
				>

				<!-- Event Card 4 -->
				<div class="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 p-4">
					<div class="bg-white shadow-lg rounded-lg overflow-hidden">
						<img class="w-full h-40 object-cover"
							src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
							alt="Event 4" />
						<div class="p-4">
							<span class="block text-sm text-gray-500 mb-2">From
								<strong>16 Aug</strong>
							</span>
							<h2 class="text-lg font-bold">Chaïm Soutine. Against the
								Current</h2>
							<p class="text-gray-600">Bern</p>
						</div>
					</div>
				</div>

				<!-- Event Card 4 -->
				<div class="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 p-4">
					<div class="bg-white shadow-lg rounded-lg overflow-hidden">
						<img class="w-full h-40 object-cover"
							src="https://media.myswitzerland.com/image/fetch/c_lfill,g_auto,w_320,h_160/f_auto,q_80,fl_keep_iptc/https://www.myswitzerland.com/-/media/dam/internet/myswitzerland/vorlagen/dev%20pm/hikes-around-the-water/meta%20page%20image/37839_32001800.jpeg"
							alt="Event 4" />
						<div class="p-4">
							<span class="block text-sm text-gray-500 mb-2">From
								<strong>16 Aug</strong>
							</span>
							<h2 class="text-lg font-bold">Chaïm Soutine. Against the
								Current</h2>
							<p class="text-gray-600">Bern</p>
						</div>
					</div>
				</div>
				<!-- Add more event cards as needed -->
			</div>
		</div>

		<!-- Newsletter Section -->
		<div
			class="newsletter-section bg-cover bg-center p-10 mt-10 rounded-lg text-white max-w-7xl mx-auto p-6"
			style="background-image: url('')">
			<div
				class="w-full mx-auto text-center bg-gray-300 bg-opacity-75 p-6 rounded-lg">
				<h2 class="text-red-700 text-3xl font-bold">Newsletter</h2>
				<p class="mt-4 text-gray-600">The monthly newsletter offers the
					best tips and key information about Switzerland as a travel
					destination, ensuring your perfect holiday.</p>
				<button
					class="mt-6 bg-red-700 hover:bg-red-500 text-white py-2 px-6 rounded-full">
					Subscribe now!</button>
			</div>
		</div>
	</div>

	<!--footer-->
	<footer id="footerContainer"
		class="flex flex-col items-center justify-center space-y-10 w-s4/5 max-w-7xl hover:bg-[#fafafa] hover:text-red-700 mx-auto"></footer>

	<script src="./innerHtmlInserter.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
	<script src="2.js"></script>

	<!-- Hotel Booking -->
	<script>
		function increment(id) {
			let count = document.getElementById(id + "Count");
			count.innerText = parseInt(count.innerText) + 1;
			updateGuestText();
		}

		function decrement(id) {
			let count = document.getElementById(id + "Count");
			if (parseInt(count.innerText) > 0) {
				count.innerText = parseInt(count.innerText) - 1;
				updateGuestText();
			}
		}

		function updateGuestText() {
			let adults = document.getElementById("adultsCount").innerText;
			let children = document.getElementById("childrenCount").innerText;
			document.getElementById("guestsText").innerText = adults
					+ " Adult, " + children + " Children";
		}

		document.getElementById("bookingBtn").addEventListener(
				"click",
				function() {
					document.getElementById("bookingForm").classList
							.remove("hidden");
					document.getElementById("bookingRestaurant").classList
							.add("hidden");
					document.getElementById("bookingTaxi").classList
							.add("hidden");
				});

		document.getElementById("bookingRes").addEventListener(
				"click",
				function() {
					document.getElementById("bookingForm").classList
							.add("hidden");
					document.getElementById("bookingRestaurant").classList
							.remove("hidden");
					document.getElementById("bookingTaxi").classList
							.add("hidden");
				});

		document.getElementById("bookingTa").addEventListener(
				"click",
				function() {
					document.getElementById("bookingForm").classList
							.add("hidden");
					document.getElementById("bookingRestaurant").classList
							.add("hidden");
					document.getElementById("bookingTaxi").classList
							.remove("hidden");
				});

		// Initialize the forms
		document.getElementById("bookingForm").classList.remove("hidden");
		document.getElementById("bookingRestaurant").classList.add("hidden");
		document.getElementById("bookingTaxi").classList.add("hidden");
	</script>

</body>
</html>
    