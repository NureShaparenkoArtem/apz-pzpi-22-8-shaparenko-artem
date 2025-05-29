import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import translationEn from "./en.json";
import translationUk from "./ua.json";

i18n
    .use(initReactI18next)
    .init({
        resources: {
            en: { translation: translationEn },
            uk: { translation: translationUk },
        },
        lng: "en",
        fallbackLng: "en",
        interpolation: {
            escapeValue: false,
        },
    });

export default i18n;
