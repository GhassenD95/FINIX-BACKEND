# 📊 **FIN'IX - DIAGRAMMES DE CLASSES**

Ce document présente l'architecture logicielle du projet **FIN'IX** sous forme de diagrammes de classes Mermaid, organisés par modules fonctionnels, suivis d'une vue globale.

---

## 👤 **1. MODULE : USER (IDENTITÉ & CONFIANCE)**
Gère les profils utilisateurs, le KYC et le score de confiance.

```mermaid
classDiagram
    class User {
        +long id
        +String name
        +String lastName
        +String email
        +String telephone
        +RoleType role
        +StatusType status
        +Integer profileCompletion
    }
    class UserDocument {
        +String typeDocuments
        +String fileUrl
        +Boolean isVerified
    }
    class TrustHistory {
        +Integer oldScore
        +Integer newScore
        +String scoreUpdateReason
    }
    User "1" *-- "0..*" UserDocument : owns
    User "1" -- "1" TrustHistory : has
    UserDocument "0..*" -- "1" User : verifiedBy
```

---

## 🏦 **2. MODULE : CRÉDIT (ACQUISITION)**
Gestion des demandes, du risque et des contrats de prêt.

```mermaid
classDiagram
    class CreditRequest {
        +Double requestedAmount
        +Integer durationMonths
        +CreditRequestStatus status
    }
    class RiskScore {
        +int totalScore
        +RiskLevel level
    }
    class CreditContract {
        +String contractNumber
        +BigDecimal amount
        +BigDecimal monthlyPayment
    }
    class Disbursement {
        +BigDecimal amount
        +DisbursementStatus status
    }
    CreditRequest "1" -- "1" RiskScore : evaluatedBy
    CreditRequest "1" -- "1" CreditContract : resultsIn
    CreditContract "1" -- "0..*" Disbursement : releases
    CreditRequest "0..*" -- "1" User : requestedBy
```

---

## 🛡️ **3. MODULE : CLAIMS & PRIMES (PROTECTION)**
Gestion des sinistres et recouvrement des mensualités d'assurance.

```mermaid
classDiagram
    class Claim {
        +String claimNumber
        +DateTime incidentDate
        +ClaimStatus status
    }
    class ClaimAssessment {
        +Double repairCost
        +Boolean isCovered
    }
    class InsurancePolicy {
        +String policyNumber
        +PolicyStatus status
    }
    class PremiumSchedule {
        +DateTime dueDate
        +Double amount
        +PremiumStatus status
    }
    Claim "1" -- "1" ClaimAssessment : appraisedBy
    Claim "0..*" -- "1" InsurancePolicy : coveredBy
    InsurancePolicy "1" *-- "0..*" PremiumSchedule : billedBy
    PremiumSchedule "0..*" -- "1" User : paidBy
```

---

## 🚗 **4. MODULE : VEHICLE (CATALOGUE & VENTE)**
Gestion du parc automobile et des transactions de vente.

```mermaid
classDiagram
    class Vehicle {
        +String brand
        +String model
        +BigDecimal price
        +VehicleStatus status
    }
    class SellerProfile {
        +String companyName
        +Double rating
    }
    class VehicleSale {
        +DateTime saleDate
        +BigDecimal finalPrice
    }
    Vehicle "0..*" -- "1" SellerProfile : listedBy
    Vehicle "1" -- "0..1" VehicleSale : soldIn
    VehicleSale "0..*" -- "1" User : buyer
```

---

## 🔐📈 **5. MODULE : SECURITY & FINANCIAL (INTELLIGENCE)**
Pilotage stratégique et conformité.

```mermaid
classDiagram
    class KPI {
        +String code
        +Double value
        +Double threshold
        +KPIStatus status
    }
    class FinancialDashboard {
        +String name
    }
    class FinancialAlert {
        +AlertSeverity severity
        +String message
    }
    FinancialDashboard "1" *-- "0..*" KPI : monitors
    KPI "1" -- "0..*" FinancialAlert : triggers
    FinancialAlert "0..*" -- "1" DecisionLog : documentedIn
```

---

## 🌍 **VUE GLOBALE DU SYSTÈME**
Interconnexion des grands piliers de FIN'IX.

```mermaid
classDiagram
    direction LR
    class User { <<Module User>> }
    class CreditRequest { <<Module Credit>> }
    class InsurancePolicy { <<Module Claims>> }
    class Vehicle { <<Module Vehicle>> }
    class KPI { <<Module Financial>> }

    User "1" -- "0..*" CreditRequest : appliedFor
    CreditRequest "0..*" -- "1" Vehicle : forPurchaseOf
    CreditRequest "1" -- "1" InsurancePolicy : requires
    User "1" -- "0..*" InsurancePolicy : insuredBy
    
    %% Analytics observing all
    KPI ..> CreditRequest : monitorsRisk
    KPI ..> InsurancePolicy : monitorsPayments
```
