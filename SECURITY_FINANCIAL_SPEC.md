# 🔐📈 **SPÉCIFICATIONS DÉTAILLÉES : SÉCURITÉ & INTELLIGENCE FINANCIÈRE**

Ce document détaille les deux derniers grands modules du projet FIN'IX, intégrant les entités techniques pour la Cybersécurité (M11) et le Pilotage Financier (M12).

---

## 🔐 **MODULE 11 : CYBERSÉCURITÉ & CONFORMITÉ**
**Objectif :** Garantir l'intégrité des données, la traçabilité des actions et la conformité aux régulations bancaires (AML/KYC).

### **1. Les Entités & Champs**

| Entité | Champs Clés | Description Fonctionnelle |
|--------|-------------|----------------------------|
| **`AuditLog`** | `action`, `user`, `entityId`, `ipAddress` | Enregistre chaque action sensible (ex: "APPROVE_CREDIT"). Indispensable pour la résolution de litiges. |
| **`UserSession`** | `sessionToken`, `deviceInfo`, `isActive` | Gère les connexions actives. Permet de détecter des double-connexions suspectes. |
| **`SecurityIncident`** | `type`, `severity`, `status` | Centralise les alertes de sécurité (ex: tentative de Brute Force). |
| **`AMLCheck`** | `checkResult`, `matchDetails`, `verifiedBy` | Vérifie si un client est sur une liste de sanctions (Anti-Blanchiment). |

### **2. Utilisation UI (Interface)**

*   **Front Office (Client) :**
    *   **Sécurité du compte :** Liste des appareils connectés avec bouton "Déconnexion à distance".
    *   **Historique :** "Dernière connexion le [Date] depuis [Ville]".
*   **Back Office (Admin/Compliance) :**
    *   **Journal d'Audit :** Moteur de recherche pour filtrer les actions par utilisateur ou par date.
    *   **Alertes AML :** Écran de validation manuelle lorsqu'un client est "Flagged" (marqué suspect).

---

## 📈 **MODULE 12 : FINANCIAL INTELLIGENCE & REPORTING**
**Objectif :** Transformer les données de crédit et de remboursement en indicateurs de performance (KPI) et en décisions stratégiques.

### **1. Les Entités (Basées sur la structure Financial)**

| Entité | Champs Clés | Description Fonctionnelle |
|--------|-------------|----------------------------|
| **`FinancialDashboard`** | `name`, `kpis` | Regroupe des indicateurs par thématique (ex: Dashboard Risque, Dashboard Croissance). |
| **`KPI`** | `code`, `value`, `threshold`, `status` | L'unité de mesure. Si `value` > `threshold`, le `status` passe en `CRITICAL`. |
| **`FinancialPeriod`** | `startDate`, `endDate`, `status` | Définit la fenêtre d'analyse (ex: Trimestre 1 2026). |
| **`DecisionRule`** | `ruleName`, `conditionExpression` | Définit une logique métier automatique (ex: "Alerte si PAR > 15%"). |
| **`FinancialAlert`** | `severity`, `message`, `resolved` | Notification générée automatiquement quand une règle est enfreinte. |
| **`DecisionLog`** | `decisionTaken`, `actor`, `decidedAt` | Archive les décisions humaines prises suite aux alertes. |

### **2. Utilisation UI (Interface)**

*   **Front Office (Client) :**
    *   *Généralement pas d'accès direct*, sauf pour le **Partner Portal** (Assureurs/Vendeurs) qui voient leurs propres KPIs de vente.
*   **Back Office (Admin Executive) :**
    *   **Dashboard Strategique :** Tableaux de bord avec graphiques (Charts.js) montrant l'évolution des KPIs.
    *   **Centre d'Alertes :** Timeline des alertes financières demandant une intervention.
    *   **Écran de Gouvernance :** Historique des logs de décisions pour les audits internes.

---

## 🚀 **ARCHITECTURE TECHNIQUE (FLOW)**

1.  **Collecte :** Les modules Crédit et Claims génèrent des données brutes.
2.  **Calcul :** Le module Financial agrège ces données dans des **KPIs** pour une **FinancialPeriod**.
3.  **Surveillance :** Les **DecisionRules** comparent les KPIs aux **Thresholds**.
4.  **Alerte :** Si anomalie, une **FinancialAlert** est créée et archivée dans le **AuditLog**.
5.  **Action :** L'admin prend une décision, enregistrée dans **DecisionLog**.

Ce système assure que l'IMF ne se contente pas de stocker des données, mais les **pilote** activement. 
