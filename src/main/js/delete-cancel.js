function confirmDelete() {
    const confirmationPrompt = document.getElementById("delete");
    confirmationPrompt.style.display = "block";

}

function cancelDelete() {
    const confirmationPrompt = document.getElementById("delete");
    confirmationPrompt.style.display = "none";
}

function deleteAd() {
    const successMessage = document.getElementById("confirm-msg");
    successMessage.style.display = "block";
}