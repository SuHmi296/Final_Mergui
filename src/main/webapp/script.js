document.getElementById('signupForm').addEventListener('submit', function(event) {
    const fullName = event.target.fullName.value;
    const email = event.target.email.value;
    const password = event.target.password.value;
    const terms = event.target.terms.checked;

    if (!fullName || !email || !password || !terms) {
        alert('Please fill in all fields and agree to the terms of services.');
        event.preventDefault();
    }
});
