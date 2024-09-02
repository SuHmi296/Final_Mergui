/**
 * 
 */
const apiKey = '9dd8bad034051483316f70db5f78ec05';
const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=Myeik&units=imperial&appid=${apiKey}`;
const forecastUrl = `https://api.openweathermap.org/data/2.5/forecast?q=Myeik&units=imperial&appid=${apiKey}`;

// Fetch current weather data
fetch(apiUrl)
    .then(response => response.json())
    .then(data => {
        document.querySelector('.temperature').textContent = `${Math.round(data.main.temp)} °F`;
        document.querySelector('.status').textContent = data.weather[0].description;
        document.getElementById('weather-icon').src = `http://openweathermap.org/img/w/${data.weather[0].icon}.png`;
        
        // Set current date and time
        const currentDate = new Date();
        document.getElementById('day').textContent = currentDate.toLocaleDateString('en-US', { weekday: 'long' });
        document.getElementById('time').textContent = currentDate.toLocaleTimeString('en-US');
    });

// Fetch 5-day forecast data
fetch(forecastUrl)
    .then(response => response.json())
    .then(data => {
        const forecastCards = document.getElementById('forecast-cards');
        const forecastList = data.list;

        // Filter forecast data to show one entry per day
        const dailyForecasts = forecastList.filter(forecast => forecast.dt_txt.includes('12:00:00'));

        dailyForecasts.forEach(forecast => {
            const card = document.createElement('div');
            card.classList.add('card');

            const dayName = new Date(forecast.dt * 1000).toLocaleDateString('en-US', { weekday: 'short' });
            const temp = `${Math.round(forecast.main.temp)} °F`;
            const icon = `http://openweathermap.org/img/w/${forecast.weather[0].icon}.png`;

            card.innerHTML = `
                <span class="day">${dayName}</span>
                <img src="${icon}" alt="${forecast.weather[0].description}">
                <span class="temp">${temp}</span>
            `;

            forecastCards.appendChild(card);
        });
    });
