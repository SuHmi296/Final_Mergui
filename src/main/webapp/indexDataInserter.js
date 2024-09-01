function fetchArticles() {
    fetch('/Mergui_Project/getAllArticles') // Adjust the URL if needed
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // Parse JSON from the response
        })
        .then(articles => {
            displayArticles(articles); // Pass articles to display function
        })
        .catch(error => {
            console.error('Error fetching articles:', error); // Handle any errors
        });
}


function displayArticles(articles) {
    const articlesContainer = document.getElementById('expertTipsContainer');
    
    // Create a single string of HTML content
    const articlesHTML = articles.map(article => `
	<div class="w-full h-[370px] overflow-hidden relative sm:w-1/2 lg:w-1/3 p-4 group">
	    <div class="bg-transparent rounded-lg overflow-hidden">
	        <div class="w-[360px] h-52 flex justify-center items-center rounded-xl">
	            <div class="w-full h-full rounded-lg transition-all overflow-hidden duration-300 group-hover:scale-95">
	                <img class="w-full h-full rounded-lg object-cover transition-all duration-300 group-hover:scale-125" 
	                     src="${article.image}" 
	                     alt="${article.title}" />
	            </div>
	        </div>
	        <div class="p-4 relative">
	            <h2 class="text-xl font-bold mb-2">${article.title}</h2>
	            <p class="text-gray-600 h-16 mb-6 line-clamp-3">${article.content}</p>
	        </div>
	    </div>	
		<a href="#" class="text-red-600 absolute bottom-0 right-10 ml-auto font-bold hover:underline">Learn more &rarr;</a>

	</div>

    `).join('');

    // Insert the HTML content into the container
    articlesContainer.innerHTML = articlesHTML;
}

// Call the function to fetch and display articles
fetchArticles();
