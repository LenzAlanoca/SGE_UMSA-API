// scripts.js - Funcionalidades para el Panel de Usuario UMSA Eventos

document.addEventListener('DOMContentLoaded', function() {
    // 1. Activar tooltips de Bootstrap
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // 2. Manejar el estado activo del sidebar
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => {
        link.addEventListener('click', function() {
            navLinks.forEach(l => l.classList.remove('active'));
            this.classList.add('active');

            // Guardar el estado activo en localStorage
            localStorage.setItem('activeNavItem', this.textContent.trim());
        });
    });

    // Restaurar el estado activo al cargar la página
    const activeNavText = localStorage.getItem('activeNavItem');
    if (activeNavText) {
        navLinks.forEach(link => {
            if (link.textContent.trim() === activeNavText) {
                link.classList.add('active');
            }
        });
    }

    // 3. Mostrar notificación de bienvenida
    if (!sessionStorage.getItem('welcomeShown')) {
        const welcomeToast = new bootstrap.Toast(document.getElementById('welcomeToast'));
        welcomeToast.show();
        sessionStorage.setItem('welcomeShown', 'true');
    }

    // 4. Manejar el contador de eventos
    updateEventCounters();

    // 5. Animación para las tarjetas de eventos al cargar
    animateEventCards();
});

// Función para actualizar contadores de eventos
function updateEventCounters() {
    // Simulación de datos - en una aplicación real esto vendría de una API
    const eventData = {
        active: 5,
        upcoming: 3,
        pending: 2
    };

    document.querySelector('.card-title[data-counter="active"]').textContent = eventData.active;
    document.querySelector('.card-title[data-counter="upcoming"]').textContent = eventData.upcoming;
    document.querySelector('.card-title[data-counter="pending"]').textContent = eventData.pending;
}

// Función para animar las tarjetas de eventos
function animateEventCards() {
    const eventCards = document.querySelectorAll('.event-card');
    eventCards.forEach((card, index) => {
        // Retraso escalonado para la animación
        setTimeout(() => {
            card.style.opacity = '1';
            card.style.transform = 'translateY(0)';
        }, 150 * index);
    });
}

// Función para mostrar modal de detalles del evento
function showEventDetails(eventId) {
    // En una aplicación real, aquí harías una petición a la API
    console.log(`Mostrando detalles del evento ${eventId}`);

    // Simulación de datos
    const eventDetails = {
        id: eventId,
        title: `Evento ${eventId}`,
        description: 'Descripción detallada del evento...',
        date: '2025-06-15',
        time: '09:00 - 12:00',
        location: 'Auditorio Principal UMSA'
    };

    // Llenar el modal con los datos
    document.getElementById('eventDetailTitle').textContent = eventDetails.title;
    document.getElementById('eventDetailDescription').textContent = eventDetails.description;
    document.getElementById('eventDetailDate').textContent = eventDetails.date;
    document.getElementById('eventDetailTime').textContent = eventDetails.time;
    document.getElementById('eventDetailLocation').textContent = eventDetails.location;

    // Mostrar el modal
    const eventModal = new bootstrap.Modal(document.getElementById('eventDetailModal'));
    eventModal.show();
}

// Función para configurar recordatorio
function setEventReminder(eventId) {
    // Simulación - en una app real usarías la API de notificaciones
    console.log(`Configurando recordatorio para evento ${eventId}`);

    const reminderToast = new bootstrap.Toast(document.getElementById('reminderToast'));
    document.getElementById('reminderMessage').textContent =
        `Recordatorio configurado para el evento #${eventId}`;
    reminderToast.show();
}

// Exportar funciones para uso global (si es necesario)
window.showEventDetails = showEventDetails;
window.setEventReminder = setEventReminder;