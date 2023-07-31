function updateAd() {
    const form = document.getElementById('updateAd');
    form.submit();
}

function cancelUpdate() {
    window.location.href = '/ads';
}

document.addEventListener('DOMContentLoaded', function () {
    const updateButton = document.getElementById('updateButton');
    const cancelButton = document.getElementById('cancelButton');

    updateButton.addEventListener('click', updateAd);
    cancelButton.addEventListener('click', cancelUpdate);
})