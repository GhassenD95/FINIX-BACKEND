# 📄 **FIN'IX - SPÉCIFICATIONS DES MODULES & ENTITÉS**

Ce document récapitule les entités implémentées dans le backend, leurs champs, et leur rôle dans les interfaces Front Office (Client/Partenaire) et Back Office (IMF).

---

## 👤 **MODULE 0: USER (UTILISATEUR & AUTH)**

**Objectif:** Gérer l'identité, les rôles et la confiance des utilisateurs.

### **1. User**
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `id` | long | Identifiant unique | Interne |
| `name` | String | Prénom | Profil, Dashboards |
| `lastName` | String | Nom de famille | Profil, Dashboards |
| `email` | String | Email (Login) | Connexion, Notifications |
| `password` | String | Mot de passe (Haché) | Connexion |
| `telephone` | String | Numéro de téléphone | Validation SMS, Contact |
| `address` | String | Adresse physique | KYC, Contrats |
| `role` | RoleType | Rôle (CLIENT, AGENT, etc.) | RBAC (Contrôle d'accès) |
| `status` | StatusType | Statut (ACTIVE, SUSPENDED) | Gestion administrative |
| `profileCompletion`| Integer| % de complétion profil | Dashboard Client |
| `trustHistory` | TrustHistory| Historique du trust score | Graphiques évolution |

**Fonctionnalités Clés:** Inscription multi-étapes, Connexion JWT, Gestion de profil.
- **Front Office:** Page Profil, Indicateur de Trust Score.
- **Back Office:** Liste des utilisateurs, Activation/Suspension de comptes.

---

## 🏦 **MODULE 1 & 2: CRÉDIT (DEMANDE & CONTRAT)**

**Objectif:** Gérer le cycle de vie d'un crédit, de la demande au décaissement.

### **1. CreditRequest** (Demande de Crédit)
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `user` | User | Demandeur | Profil demandeur |
| `vehicleId` | Long | Véhicule sélectionné | Détails financement |
| `requestedAmount` | Double | Montant du prêt | Calculateur mensualités |
| `durationMonths` | Integer| Durée en mois | Calculateur mensualités |
| `status` | Enum | DRAFT, SUBMITTED, etc. | Suivi statut (Timeline) |
| `riskScore` | RiskScore | Score calculé | Évaluation (BackOffice) |

### **2. CreditContract** (Contrat de Crédit)
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `contractNumber` | String | Identifiant unique contrat | Référence documents |
| `amount` | Double | Montant prêté final | Plan de remboursement |
| `interestRate` | Double | Taux appliqué | Simulation, Contrat |
| `status` | Enum | SIGNED, ACTIVE, etc. | Statut du prêt |
| `contractPdfUrl` | String | Lien vers le PDF | Visualisation/Téléchargement |

**Fonctionnalités Clés:** Soumission de demande, Scoring automatique, Génération de contrat PDF, Signature électronique.
- **Front Office:** Formulaire de demande, Signature du contrat.
- **Back Office:** Dashboard d'évaluation, Workflow de décaissement.

---

## 💥 **ÉTUDIANT 4: MODULES SINISTRES & PRIMES**

**Objectif:** Gérer les déclarations de sinistres et l'encaissement des primes d'assurance.

### **1. Claim** (Sinistres)
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `claimNumber` | String | Référence du sinistre | Suivi dossier |
| `description` | String | Circonstances | Formulaire déclaration |
| `incidentDate` | DateTime | Date du sinistre | Chronologie |
| `status` | Enum | SUBMITTED, APPROVED, etc. | Suivi en temps réel |
| `claimType` | Enum | ACCIDENT, THEFT, etc. | Filtres catalogue |
| `fraudScore` | Integer| Score de suspicion | Alerte BackOffice |

### **2. ClaimAssessment** (Évaluation)
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `expertNotes` | String | Observations de l'expert | Rapport d'expertise |
| `estimatedRepairCost`| Double | Coût estimé | Calcul indemnisation |
| `isCovered` | Boolean| Éligibilité assurance | Décision finale |

### **3. PremiumSchedule** (Primes)
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `installmentNumber` | Integer| Numéro d'échéance | Calendrier paiements |
| `dueDate` | DateTime | Date limite de paiement | Alertes / Rappels |
| `amount` | Double | Montant de la prime | Dashboard financier |
| `status` | Enum | PENDING, PAID, OVERDUE | Statut couverture |

### **4. PartnerCommission** (Commissions)
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `period` | String | Mois/Année (ex: 2026-02) | Reporting partenaire |
| `commissionAmount` | Double | Montant dû au partenaire | Dashboard Partenaire |
| `status` | Enum | CALCULATED, PAID | Suivi règlements |

**Fonctionnalités Clés:** Déclaration de sinistre, Évaluation experte, Encaissement multi-méthodes, Calcul de commissions.
- **Front Office:** Déclaration de sinistre, Paiement de primes.
- **Back Office:** Évaluation par l'expert, Suivi des commissions partenaires.
