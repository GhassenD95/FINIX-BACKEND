# 📄 **FIN'IX - SPÉCIFICATIONS DES MODULES & ENTITÉS**

Ce document récapitule les entités implémentées dans le backend, structurées par **Grands Modules** et **Sous-Modules**, avec leurs rôles respectifs dans le Front Office (Client/Partenaire) et Back Office (IMF).

---

## 👤 **GRANDS MODULES 0: USER (UTILISATEUR & CONFIANCE)**

**Objectif:** Gérer l'identité numérique, la sécurité et le score de confiance.

### **Sous-Module 0.1: Identity & Profiles**
| Entité | Champs Clés | Rôle & Usage UI |
|--------|-------------|-----------------|
| **User** | `name`, `email`, `role`, `status` | Profil utilisateur, Gestion des accès (BackOffice). |
| **UserDocument** | `documentType`, `fileUrl`, `verified` | KYC (Know Your Customer) - Upload documents. |
| **RoleType** | `CUSTOMER`, `AGENT`, `AGENT_CR`, etc. | RBAC - Détermine les menus et actions accessibles. |

### **Sous-Module 0.2: Trust & Security**
| Entité | Champs Clés | Rôle & Usage UI |
|--------|-------------|-----------------|
| **TrustHistory** | `score`, `reason`, `date` | Suivi de l'évolution du Trust Score (Graphiques Client). |
| **StatusType** | `ACTIVE`, `PENDING`, `SUSPENDED` | Cycle de vie du compte utilisateur. |

---

## 🏦 **GRAND MODULE 1: CREDIT (DEMANDE & CONTRAT)**

**Objectif:** Digitaliser tout le processus d'acquisition de crédit.

### **Sous-Module 1.1: Request & Risk (Demande & Évaluation)**
| Entité | Champs Clés | Rôle & Usage UI |
|--------|-------------|-----------------|
| **CreditRequest** | `requestedAmount`, `duration`, `status` | Formulaire de demande (Front), Liste des dossiers (Back). |
| **RiskScore** | `totalScore`, `riskLevel` | Indicateur de risque pour décision IMF (BackOffice). |
| **CreditDocument** | `documentType`, `fileUrl` | Justificatifs de revenus, relevés bancaires (KYC Crédit). |
| **CreditHistory** | `totalRequests`, `totalApproved` | Historique pour scoring automatique. |

### **Sous-Module 1.2: Contract & Disbursement (Contrat & Décaissement)**
| Entité | Champs Clés | Rôle & Usage UI |
|--------|-------------|-----------------|
| **CreditContract** | `contractNumber`, `amount`, `pdfUrl` | Visualisation contrat, Signature électronique (Front). |
| **ContractVersion** | `versionNumber`, `changeDescription` | Historique des modifications du contrat. |
| **Disbursement** | `amount`, `status`, `recipient` | Workflow de paiement au vendeur (BackOffice). |

---

## �️ **GRAND MODULE 2: CLAIMS & PREMIUNS (SINISTRES & PRIMES)**

**Objectif:** Gérer la protection du véhicule (Sinistres) et le recouvrement des primes.

### **Sous-Module 2.1: Sinistres (Claims Management)**
| Entité | Champs Clés | Rôle & Usage UI |
|--------|-------------|-----------------|
| **Claim** | `claimNumber`, `incidentDate`, `status` | Déclaration de sinistre (Front), Suivi dossier. |
| **ClaimAssessment** | `expertNotes`, `repairCost`, `isCovered` | Expertise terrain et décision d'indemnisation (Back). |
| **ClaimDocument** | `documentType`, `fileUrl` | Photos du sinistre, Constat amiable, Rapport de police. |

### **Sous-Module 2.2: Primes (Insurance & Payments)**
| Entité | Champs Clés | Rôle & Usage UI |
|--------|-------------|-----------------|
| **InsurancePolicy** | `policyNumber`, `startDate`, `status` | Détails de la couverture active (Dashboard Front). |
| **PremiumSchedule** | `dueDate`, `amount`, `status` | Calendrier des paiements (Front), Alertes retards. |
| **PremiumPayment** | `paymentDate`, `method`, `reference` | Historique des paiements de primes effectués. |
| **PartnerCommission**| `period`, `commissionAmount`, `status` | Reporting financier pour l'assureur partenaire. |

---

## 🚗 **NEXT MODULES (PLANNED)**
- **Vehicle Module:** Catalogue, Inspection, Vente.
- **Analytics Module:** Reporting executive, ML Predictions.
