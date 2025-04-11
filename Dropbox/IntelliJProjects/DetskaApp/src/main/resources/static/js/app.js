document.addEventListener('DOMContentLoaded', () => {
console.log('Načítavam elementy...');

const resultMessage = document.getElementById('resultMessage');
const userAnswer = document.getElementById('answer');
const generateExampleButton = document.getElementById('generateExampleButton');
const stopButton = document.getElementById('stopButton');
const gradeSelect = document.getElementById('grade');
const BASE_URL = 'http://localhost:8080';

// Vytvorenie zvukových efektov
const correctSound = new Audio('/static/sounds/crowd-cheer-in-school-auditorium-236699.mp3');
const incorrectSound = new Audio('/static/sounds/fail-144746.mp3');

// Preload zvukov
correctSound.load();
incorrectSound.load();

// Pridanie event listenerov pre ladenie zvuku
correctSound.addEventListener('error', (e) => {
    console.error('Chyba pri načítaní správneho zvuku:', e);
});

incorrectSound.addEventListener('error', (e) => {
    console.error('Chyba pri načítaní nesprávneho zvuku:', e);
});

// Funkcia na prehratie zvuku s ošetrením chýb a časovým limitom
const playSound = async (sound) => {
    try {
        // Nastavíme začiatok prehrávania na 0
        sound.currentTime = 0;
        await sound.play();
        
        // Zastavíme zvuk po 1.5 sekundách
        setTimeout(() => {
            sound.pause();
            sound.currentTime = 0;
        }, 1500);
    } catch (error) {
        console.error('Chyba pri prehrávaní zvuku:', error);
    }
};

// Nastavenie hlasitosti (hodnota od 0.0 do 1.0)
correctSound.volume = 0.2;   // Znížená hlasitosť pre správnu odpoveď
incorrectSound.volume = 0.1; // Ešte nižšia hlasitosť pre nesprávnu odpoveď (aby nebola príliš nepríjemná)

let currentExample = null;

console.log("resultMessage:", resultMessage);
console.log("userAnswer:", userAnswer);
console.log("generateExampleButton:", generateExampleButton);
console.log("stopButton:", stopButton);
console.log("gradeSelect:", gradeSelect);

if (!resultMessage) console.error("resultMessage chyba v HTML!");
if (!userAnswer) console.error("userAnswer chyba v HTML!");
if (!generateExampleButton) console.error("generateExampleButton chyba v HTML!");
if (!stopButton) console.error("stopButton chyba v HTML!");
if (!gradeSelect) console.error("gradeSelect chyba v HTML!");

// Funkcia na kontrolu odpovede
const checkAnswer = () => {
    const answer = document.getElementById('answer').value;

    if (answer && currentExample) {
        currentExample.userAnswer = parseInt(answer);
        
        fetch(`${BASE_URL}/api/check-answer`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(currentExample)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(result => {
            console.log("Prijatá odpoveď:", result);
            if (result.correct) {
                playSound(correctSound);
                document.getElementById('resultMessage').innerText = 'Správna odpoveď!';
                setTimeout(() => {
                    generateNewExample();
                }, 1000);
            } else {
                playSound(incorrectSound);
                document.getElementById('resultMessage').innerText = 'Nesprávne, skúste znova!';
                document.getElementById('answer').value = '';
            }
        })
        .catch(error => {
            console.error('Chyba pri overovaní odpovede:', error);
            document.getElementById('resultMessage').innerText = 'Chyba pri overovaní odpovede';
        });
    } else if (!currentExample) {
        document.getElementById('resultMessage').innerText = 'Najprv vygenerujte príklad!';
    } else {
        document.getElementById('resultMessage').innerText = 'Zadajte odpoveď pred kontrolou!';
    }
};

// Funkcia na generovanie nového príkladu
const generateNewExample = () => {
    const grade = gradeSelect.value;
    console.log("Generujem príklad pre ročník:", grade);

    fetch(`${BASE_URL}/api/generate?grade=${grade}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log("Prijatá odpoveď:", data);
            currentExample = data;
            document.getElementById('exampleText').innerText = data.question;
            document.getElementById('answer').value = '';
            document.getElementById('resultMessage').innerText = '';
        })
        .catch(error => {
            console.error('Chyba pri načítaní príkladu:', error);
            document.getElementById('resultMessage').innerText = 'Chyba pri načítaní príkladu';
        });
};

// Pridáme event listener na input pole pre stlačenie Enter
userAnswer.addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
        event.preventDefault(); // Zabránime odoslaniu formulára
        checkAnswer();
    }
});

// Event listener pre tlačidlo na generovanie príkladu
generateExampleButton.addEventListener('click', generateNewExample);

// Event listener pre tlačidlo "Ďalší príklad"
document.getElementById('next').addEventListener('click', checkAnswer);

// Event listener pre STOP tlačidlo
document.getElementById('stopButton').addEventListener('click', () => {
    fetch(`${BASE_URL}/api/progress`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            console.log("Prijatá odpoveď:", data);
            document.getElementById('resultMessage').innerText = data;
        })
        .catch(error => {
            console.error('Chyba pri načítaní štatistiky:', error);
            document.getElementById('resultMessage').innerText = 'Chyba pri načítaní štatistiky';
        });
});

// Event listener pre tlačidlo na konverziu jednotiek
document.getElementById('convertUnitsButton').addEventListener('click', () => {
    fetch(`${BASE_URL}/api/conversion`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log("Prijatá odpoveď:", data);
            currentExample = data;
            document.getElementById('exampleText').innerText = data.question;
            document.getElementById('answer').value = '';
            document.getElementById('resultMessage').innerText = '';
        })
        .catch(error => {
            console.error('Chyba pri načítaní konverzného príkladu:', error);
            document.getElementById('resultMessage').innerText = 'Chyba pri načítaní konverzného príkladu';
        });
});
});






