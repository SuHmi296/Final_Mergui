/**
 * 
 */

   function logout() {
    console.log("Logout Fn Run")
      // Clear LocalStorage
      localStorage.removeItem('name');
      localStorage.removeItem('email');
      localStorage.removeItem('userType');
	  localStorage.setItem('isLoggedIn', false);

      
      // Clear the session on the server
      fetch('/Mergui_Project/logoutServlet', { // Adjust the URL as needed
          method: 'POST', // Use POST or GET based on your server configuration
          credentials: 'include' // Include credentials (cookies) with the request
      })
      .then(response => {
          if (response.ok) {
              // Redirect to home page after successful logout
              window.location.href = '/Mergui_Project/index.html';
          } else {
              console.error('Logout failed');
          }
      })
      .catch(error => {
          console.error('Error during logout:', error);
      });
  }