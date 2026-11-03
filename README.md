# Currency Converter - TP Android Kotlin

Application pédagogique de conversion entre USD, EUR, GBP et XOF, avec taux fixes (USD comme référence).

## Ouvrir le projet
1. Décompresser l'archive.
2. Dans Android Studio, choisir **Open** et sélectionner le dossier `CurrencyConverterTP`.
3. Laisser Gradle synchroniser le projet.
4. Lancer `app` sur un appareil ou un émulateur Android (API 23+).

En ligne de commande Windows, lancer `gradlew.bat assembleDebug` depuis le dossier du projet. Le projet utilise Gradle 8.7; Java et Kotlin compilent tous deux en cible JVM 17.

Le projet utilise une Empty Views Activity, Kotlin, XML, View Binding et un `ConstraintLayout` contenant un `TableLayout` pour le clavier. L'écran est verrouillé en portrait.

## Taux pédagogiques
Taux définis par USD: USD = 1.0, EUR = 0.92, GBP = 0.79, XOF = 610.0. Conversion: montant × taux source / taux cible. Ces taux sont fixes et uniquement destinés au TP.

## Essais suggérés
- USD → EUR, 120 : 110.40
- EUR → USD, 100 : 108.70
- GBP → XOF, 10 : 7 721.52
- Même devise USD → USD, 25 : 25.00

Utiliser les touches numériques; `C` efface; `.` ajoute le séparateur décimal. Le bouton ⇅ inverse les devises. Une saisie vide, mal formée ou hors plage est signalée par Toast.

## Captures
Le dossier `screenshots/` contient des consignes de capture. Aucun émulateur utilisable n'était connecté pendant la préparation; aucun screenshot n'a été simulé.
