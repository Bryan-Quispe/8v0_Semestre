import puppeteer from 'puppeteer';

async function obtenerCookieMiESPE(usuario, contraseña) {
    // headless: false hará que veas cómo se abre Chrome y escribe solo.
    // Cuando ya lo vayas a subir a producción (tu backend final), lo cambias a true.
    const browser = await puppeteer.launch({ headless: false }); 
    const page = await browser.newPage();

    try {
        console.log("1. Navegando a la página de asistencias para forzar el login...");
        await page.goto('https://autoserviciop.espe.edu.ec/StudentSelfService/ssb/studentAttendanceTracking#!/', { waitUntil: 'networkidle2' });

        console.log("2. Esperando que cargue el formulario...");
        // Usamos el ID exacto que descubriste en la captura
        await page.waitForSelector('#usernameUserInput', { timeout: 15000 });

        console.log("3. Escribiendo credenciales...");
        await page.type('#usernameUserInput', usuario); 
        await page.type('#password', contraseña); 

        console.log("4. Haciendo clic en Sign In...");
        await Promise.all([
            page.waitForNavigation({ waitUntil: 'networkidle2' }), // Espera a que la redirección termine
            page.click('button[type="submit"]') 
        ]);

        console.log("5. Extrayendo la Cookie...");
        const cookies = await page.cookies();
        const jsessionIdCookie = cookies.find(c => c.name === 'JSESSIONID');

        await browser.close();

        if (jsessionIdCookie) {
            console.log("¡ÉXITO! Tu JSESSIONID es:", jsessionIdCookie.value);
            return jsessionIdCookie.value;
        } else {
            console.log("No se encontró la cookie. Revisa si la contraseña es correcta.");
            return null;
        }

    } catch (error) {
        console.error("Error durante la automatización:", error);
        await browser.close();
        return null;
    }
}

// ---- PARA PROBARLO AHORA MISMO ----
// Reemplaza con tus datos reales.
// Recuerda usar Node.js para correr este archivo.
obtenerCookieMiESPE('brquispe', 'S@bryromero406728');