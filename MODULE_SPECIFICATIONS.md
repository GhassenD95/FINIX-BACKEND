# 📄 **FIN'IX - SPÉCIFICATIONS EXHAUSTIVES DES MODULES & ENTITÉS**

Ce document est la source unique de vérité pour toutes les entités implémentées dans le backend de **FIN'IX**. Il détaille chaque champ, sa logique métier, et son utilisation précise dans les interfaces **Front Office** et **Back Office**.

---

## 👤 **1. GRAND MODULE : USER (UTILISATEUR & CONFIANCE)**
**Responsabilité :** Gestion de l'identité, de l'onboarding (KYC) et de la réputation de l'utilisateur.

### **1.1. Entity: User**
*Cœur de l'application, représentant toute personne physique interagissant avec le système.*

| Champ | Type | Description | Logiciel / Règle |
|-------|------|-------------|------------------|
| `id` | long | ID technique unique | Auto-incrémenté. |
| `name` / `lastName` | String | Identité civile | Utilisé pour la personnalisation UI et les contrats. |
| `email` | String | Identifiant de connexion | Unique. Utilisé pour l'envoi de rapports/notifications. |
| `password` | String | Secret de connexion | Toujours haché (BCrypt). |
| `telephone` | String | Contact mobile | Requis pour la validation par OTP. |
| `address` | String | Domicile physique | Requis pour les contrats de crédit et assurance. |
| `role` | RoleType | Rôle système | CLIENT, AGENT, IMF_ADMIN, SELLER, PARTNER. |
| `status` | StatusType | État du compte | `ACTIVE`, `PENDING` (KYC en cours), `SUSPENDED`. |
| `profileCompletion` | Integer | Score d'onboarding | 0 à 100%. Incrémenté à chaque document uploadé. |
| `documents` | Set | Liste des documents | Relation avec `UserDocument`. |
| `trustHistory` | Object | Historique de réputation | Lien vers le dernier état du Trust Score. |

*   **Front Office :** Page "Mon Profil", Dashboard (Score de complétion), Écran de Login/Register.
*   **Back Office :** Gestion des utilisateurs (Liste, Recherche, Suspension), Dashboard KPI (Nombre de nouveaux clients).

### **1.2. Entity: UserDocument (KYC)**
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `typeDocuments` | String | Nature du document | CIN Recto, Selfie, Justificatif de domicile. |
| `fileUrl` | String | Lien de stockage | Visualisation PDF ou Image dans l'admin. |
| `isVerified` | Boolean | Statut de validation | Marqueur vert dans le profil client après vérification. |
| `verificationAgent` | User | Agent responsable | Audit : Savoir qui a validé le document (BackOffice). |

### **1.3. Entity: TrustHistory**
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `oldScore` / `newScore` | Integer | Variation du score | Graphique linéaire de progression du score (Front). |
| `scoreUpdateReason` | String | Raison du changement | "Paiement à l'heure", "Document validé", etc. |

---

## 🏦 **2. GRAND MODULE : CREDIT (CRÉDIT ACQUISITION)**
**Responsabilité :** Gestion du cycle de vie du prêt, du scoring de risque au décaissement final.

### **2.1. Entity: CreditRequest**
| Champ | Type | Description | Logiciel / Règle |
|-------|------|-------------|------------------|
| `requestedAmount` | Double | Montant souhaité | Base de calcul pour les mensualités. |
| `durationMonths` | Integer | Durée du prêt | Détermine l'échéancier (ex: 12, 24, 36 mois). |
| `status` | Enum | État de la demande | `DRAFT`, `SUBMITTED`, `UNDER_REVIEW`, `APPROVED`, `REJECTED`. |
| `riskScore` | RiskScore | Évaluation technique | Calculé automatiquement après soumission. |

*   **Front Office :** Simulateur de crédit, Tunnel de demande de prêt, Page "Mes Demandes".
*   **Back Office :** Liste de travail (Worklist) pour les analystes de crédit.

### **2.2. Entity: RiskScore (Algorithme 0..100)**
| Champ | Type | Description | Poids |
|-------|------|-------------|-------|
| `trustScore` | int | Score interne utilisateur | 35% |
| `dtiScore` | int | Debt-to-Income (Endettement) | 25% |
| `paymentHistoryScore` | int | Historique de paiement | 20% |
| `downPaymentScore` | int | Apport initial | 10% |
| `socialApprovalScore` | int | Validation sociale | 10% |
| **`totalScore`** | int | Résultat final 0..100 | Détermine l'éligibilité. |
| `level` | RiskLevel | Catégorie | `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`. |

### **2.3. Entity: CreditContract**
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `contractNumber` | String | Référence légale | Ex: "CT-2026-001". |
| `totalToRepay` | BigDecimal | Montant total dû | Affichage du coût total du crédit (Transparence). |
| `monthlyPayment` | BigDecimal | Mensualité fixe | Affiché sur le bouton de paiement mensuel (Front). |
| `pdfUrl` | String | Lien document légal | Lien "Télécharger mon contrat" (Front). |

### **2.4. Entity: Disbursement (Décaissement)**
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `amount` | BigDecimal | Somme déboursée | Généralement `CreditContract.amount`. |
| `iban` | String | Compte bénéficiaire | RIB du vendeur ou du client. |
| `beneficiary` | String | Type de destinataire | SELLER (Vendeur véhicule) ou CLIENT. |
| `status` | Enum | État du virement | `PENDING`, `PROCESSED`, `FAILED`. |

---

## 🛡️ **3. GRAND MODULE : CLAIMS & PRIMES (PROTECTION & PAIEMENTS)**
**Responsabilité :** Gestion des sinistres assurance et recouvrement des mensualités de primes.

### **3.1. Sub-Module: Sinistres (Claims)**

#### **Entity: Claim**
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `claimNumber` | String | Réf unique sinistre | Identifiant pour le client et l'expert. |
| `incidentDate` | DateTime | Date de l'événement | Tri chronologique. |
| `claimType` | Enum | Nature du choc | Accident, Vol, Incendie, Catastrophe Naturelle. |
| `totalDamageAmount` | Double | Montant des dégâts | Saisi par l'expert dans le rapport (BackOffice). |
| `fraudScore` | Integer | IA Anti-fraude | Alerte rouge si > 70 (BackOffice). |

#### **Entity: ClaimAssessment (Expertise)**
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `expertNotes` | String | Rapport d'expertise | Justificatif écrit de la décision. |
| `isCovered` | Boolean | Décision finale | Active le flux de remboursement si `true`. |

### **3.2. Sub-Module: Primes (Insurance & Payments)**

#### **Entity: InsurancePolicy**
*Le lien entre le crédit et l'assurance.*
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `policyNumber` | String | Référence assurance | Affiché sur le certificat d'assurance (Front). |
| `status` | String | État de couverture | `ACTIVE`, `PENDING`, `EXPIRED`, `CANCELLED`. |

#### **Entity: PremiumSchedule (Échéancier)**
| Champ | Type | Description | Logiciel / Règle |
|-------|------|-------------|------------------|
| `installmentNumber` | Integer | Rang du mois | Ex: 3/12 mensualités. |
| `dueDate` | DateTime | Date d'exigibilité | Déclenche des notifications SMS à J-3. |
| `status` | Enum | État du paiement | `PENDING`, `PAID`, `OVERDUE` (en retard), `WAIVED`. |

#### **Entity: PremiumPayment (Action de Paiement)**
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `amountPaid` | Double | Somme encaissée | Doit être égale à `PremiumSchedule.amount`. |
| `paymentMethod` | String | Canal utilisé | D17, Carte, Cash (auprès d'un agent). |
| `transactionReference`| String | Preuve technique | Lien vers la passerelle de paiement. |

#### **Entity: PartnerCommission (Reporting Assureur)**
| Champ | Type | Description | Usage UI |
|-------|------|-------------|----------|
| `period` | String | Mois concerné | Ex: "2026-02". |
| `commissionAmount` | Double | Revenu partenaire | Dashboard Partenaire Assureur (B2B). |
| `status` | Enum | Flux financier | `CALCULATED` (Prêt à facturer), `PAID` (Réglé). |
