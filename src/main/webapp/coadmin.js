// Example data for rooms
const rooms = [
    {
        room_number: "101",
        room_type: "Single",
        price: 100.0,
        promotion_price: 80.0,
        promotion_start_date: "2024-08-01",
        promotion_end_date: "2024-08-10",
        description: "A cozy single room.",
        is_available: true
    },
    {
        room_number: "102",
        room_type: "Double",
        price: 150.0,
        promotion_price: null,
        promotion_start_date: null,
        promotion_end_date: null,
        description: "A spacious double room.",
        is_available: false
    }
    // Add more rooms as needed
];

// Example data for transportation
const transportation = [
    {
        transportation_id: "T001",
        type: "Taxi",
        price: 20.0,
        description: "Comfortable taxi service."
    },
    {
        transportation_id: "T002",
        type: "Bus",
        price: 5.0,
        description: "Affordable bus rides."
    }
    // Add more transportation options as needed
];

// Example data for articles
const articles = [
    {
        article_id: "A001",
        title: "The Best Travel Destinations",
        content: "Explore the world's top travel destinations..."
    },
    {
        article_id: "A002",
        title: "Travel Tips and Tricks",
        content: "Here are some useful travel tips..."
    }
    // Add more articles as needed
];

// Example data for restaurants
const restaurants = [
    {
        restaurant_id: "R001",
        name: "Gourmet Bistro",
        type: "French",
        price_range: "$$$"
    },
    {
        restaurant_id: "R002",
        name: "Pizza Palace",
        type: "Italian",
        price_range: "$$"
    }
    // Add more restaurants as needed
];


// Column Definitions
const roomColumns = [
    { header: "Room Number", accessor: (item) => item.room_number },
    { header: "Room Type", accessor: (item) => item.room_type },
    { header: "Price", accessor: (item) => item.price.toFixed(2) },
    {
        header: "Promotion Price",
        accessor: (item) =>
            item.promotion_price !== null ? item.promotion_price.toFixed(2) : "N/A",
    },
    {
        header: "Promotion Start Date",
        accessor: (item) => item.promotion_start_date || "N/A",
    },
    {
        header: "Promotion End Date",
        accessor: (item) => item.promotion_end_date || "N/A",
    },
    { header: "Description", accessor: (item) => item.description },
    {
        header: "Availability",
        accessor: (item) => (item.is_available ? "Available" : "Unavailable"),
    },
];

const transportationColumns = [
    { header: "ID", accessor: (item) => item.transportation_id },
    { header: "Type", accessor: (item) => item.type },
    { header: "Price", accessor: (item) => item.price.toFixed(2) },
    { header: "Description", accessor: (item) => item.description },
];

const articleColumns = [
    { header: "Title", accessor: (item) => item.title },
    {
        header: "Content",
        accessor: (item) => item.content.substring(0, 50) + "...",
    },
];

const restaurantColumns = [
    { header: "Name", accessor: (item) => item.name },
    { header: "Type", accessor: (item) => item.type },
    { header: "Price Range", accessor: (item) => item.price_range },
];



// function generateTable(data, columns) {
//     let tableHTML = `
//           <table class="min-w-full border-collapse border border-gray-300">
//               <thead>
//                   <tr>
//                       ${columns
//                         .map(
//                           (col) =>
//                             `<th class="border border-gray-300 p-2">${col.header}</th>`
//                         )
//                         .join("")}
//                   </tr>
//               </thead>
//               <tbody>
//       `;
  
//     data.forEach((item) => {
//       tableHTML += `
//               <tr>
//                   ${columns
//                     .map(
//                       (col) =>
//                         `<td class="border border-gray-300 p-2">${col.accessor(
//                           item
//                         )}</td>`
//                     )
//                     .join("")}
//               </tr>
//           `;
//     });
  
//     tableHTML += `
//               </tbody>
//           </table>
//       `;
  
//     return tableHTML;
//   }

function generateTable(data, columns, type) {
    let tableHTML = `
        <table class="min-w-full border-collapse border border-gray-300">
            <thead>
                <tr>
                    ${columns
                      .map(
                        (col) =>
                          `<th class="border border-gray-300 p-2">${col.header}</th>`
                      )
                      .join("")}
                    <th class="border border-gray-300 p-2">Actions</th>
                </tr>
            </thead>
            <tbody>
    `;

    data.forEach((item) => {
        // Use JSON.stringify with additional handling for special characters
        const itemJSON = JSON.stringify(item).replace(/'/g, "\\'").replace(/"/g, '&quot;');

        tableHTML += `
            <tr onclick="openDrawer('${type}', '${itemJSON}')" style="cursor: pointer;">
                ${columns
                  .map(
                    (col) =>
                      `<td class="border border-gray-300 p-2">${col.accessor(item)}</td>`
                  )
                  .join("")}
                <td class="border border-gray-300 p-2">
                    <button onclick="event.stopPropagation(); openDrawer('${type}', '${itemJSON}')">Edit</button>
                </td>
            </tr>
        `;
    });

    tableHTML += `
            </tbody>
        </table>
    `;

    return tableHTML;
}

  
  function sortData(data, criteria) {
    return data.sort((a, b) => {
      if (typeof a[criteria] === "string") {
        return a[criteria].localeCompare(b[criteria]);
      } else {
        return (a[criteria] || 0) - (b[criteria] || 0);
      }
    });
  }
  
  function filterData(data, query, columns) {
    return data.filter((item) => {
      return columns.some((col) =>
        col.accessor(item).toString().toLowerCase().includes(query.toLowerCase())
      );
    });
  }
  
  function updateTable(data, columns, containerId, searchId, sortId) {
    const searchQuery = document.getElementById(searchId).value;
    const sortCriteria = document.getElementById(sortId).value;
  
    let filteredData = filterData(data, searchQuery, columns);
  
    if (sortCriteria !== "none") {
      filteredData = sortData(filteredData, sortCriteria);
    }
  
    document.getElementById(containerId).innerHTML = generateTable(
      filteredData,
      columns
    );
  }
  
  // Event listener setup
  function setupTableInteractions(data, columns, containerId, searchId, sortId) {
    document
      .getElementById(searchId)
      .addEventListener("input", () =>
        updateTable(data, columns, containerId, searchId, sortId)
      );
  
    document
      .getElementById(sortId)
      .addEventListener("change", () =>
        updateTable(data, columns, containerId, searchId, sortId)
      );
  
    // Initial rendering of the table
    updateTable(data, columns, containerId, searchId, sortId);
  }
  
  document.addEventListener("DOMContentLoaded", function () {
    // Setup for each table
    setupTableInteractions(
      rooms,
      roomColumns,
      "tableContainerRooms",
      "searchInputRooms",
      "sortSelectRooms"
    );
    
    setupTableInteractions(
      transportation,
      transportationColumns,
      "tableContainerTransportation",
      "searchInputTransportation",
      "sortSelectTransportation"
    );
    
    setupTableInteractions(
      articles,
      articleColumns,
      "tableContainerArticles",
      "searchInputArticles",
      "sortSelectArticles"
    );
    
    setupTableInteractions(
      restaurants,
      restaurantColumns,
      "tableContainerRestaurants",
      "searchInputRestaurants",
      "sortSelectRestaurants"
    );
  });
  