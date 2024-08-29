// admin.js
document.getElementById('toggleCoAdminManagement').addEventListener('click', function() {
    toggleSection('coAdminManagement');
});

document.getElementById('toggleCustomerManagement').addEventListener('click', function() {
    toggleSection('customerManagement');
});

document.getElementById('toggleActivationCodes').addEventListener('click', function() {
    toggleSection('activationCodes');
});

document.getElementById('toggleArticleManagement').addEventListener('click', function() {
    toggleSection('articleManagement');
});

function toggleSection(sectionId) {
    const sections = ['coAdminManagement', 'customerManagement', 'activationCodes', 'articleManagement'];
    sections.forEach(function(id) {
        document.getElementById(id).classList.add('hidden');
    });
    document.getElementById(sectionId).classList.remove('hidden');
}
