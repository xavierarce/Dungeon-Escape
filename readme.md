
# Cahier des Charges — Dungeon Scape

### 1. **Contexte et Objectif du Projet**

Développer un jeu vidéo en Java, intitulé  **Dungeon Scape** , basé sur un système de combats au tour par tour dans un univers médiéval-fantastique. Le joueur incarne un héros qui doit progresser à travers plusieurs donjons en affrontant des ennemis, gagnant de l’expérience, de l’or et des objets.

---

### 2. **Fonctionnalités Principales**

#### 2.1. Sélection du personnage 

* Le joueur doit pouvoir choisir parmi 3 classes de personnage au début du jeu :
  * **Guerrier** : équilibré en attaque et défense.
  * **Assassin** : haute attaque, faible défense.
  * **Tank** : haute défense, faible attaque.

---

#### 2.2. Début immédiat du jeu

* Après la sélection, le joueur commence directement le premier combat sans introduction ou menu supplémentaire.

---

#### 2.3. Système de combat

* Combat en boucle tour par tour entre le joueur et un ennemi.
* Chaque tour, le joueur effectue une attaque.
* L’ennemi effectue une action générée par un système d’intelligence artificielle (via OLLAMA).
* Le combat continue jusqu’à ce que la vie (HP) du joueur ou de l’ennemi atteigne zéro.

---

#### 2.4. Gestion des issues du combat

* **Perte du joueur (HP ≤ 0)** : fin du jeu (GAME OVER).
* **Victoire du joueur (HP ennemi ≤ 0)** :
  * Gain d’expérience (EXP).
  * Récompense en argent (loot money).
  * Récompense en objets (loot items).

---

#### 2.5. Progression et boutique

* Tous les 10 donjons terminés, le joueur accède à une phase narrative et peut acheter des objets dans une boutique.
* La boutique permet d’acheter des équipements, potions, ou améliorations avec l’argent gagné.

---

### 3. **Contraintes Techniques**

* Langage de programmation : **Java**
* Interface : **Console (textuelle)** pour la version initiale.
* Intégration avec OLLAMA pour la génération des actions ennemies.
* Architecture orientée objet, avec classes claires pour joueurs, ennemis, combats, objets, etc.

---
