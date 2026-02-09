# FINIX-BACKEND Module Specifications

## Application Overview

**FINIX** is a comprehensive digital micro-finance and micro-insurance platform designed for low-income users. The platform facilitates vehicle financing, insurance, sales, and repayment management with integrated risk assessment and financial monitoring capabilities.

### Core Business Flow
1. **User Registration** → Users register as Clients, Sellers, or IMF Agents
2. **Vehicle Listing** → Sellers list vehicles for sale
3. **Credit Application** → Clients apply for vehicle financing
4. **Risk Assessment** → System evaluates creditworthiness
5. **Insurance Bundling** → Insurance products offered with credit
6. **Contract Execution** → Credit contracts and insurance policies created
7. **Repayment Management** → Payment schedules, reminders, and collections
8. **Claims Processing** → Insurance claims handling
9. **Financial Monitoring** → Real-time dashboards and KPI tracking

---

## Module 1: User Module

**Purpose**: Manages user accounts, authentication, roles, and trust scoring across the platform.

### Entities

#### 1. **User**
- **Purpose**: Core entity representing all platform users (Clients, Sellers, IMF Agents, IMF Admins)
- **Key Fields**: id, name, lastName, email, password, telephone, address, role, status, profileCompletion
- **Relationships**: 
  - Has many `UserDocument`
  - Has one `TrustHistory`
- **Use Case**: Central user management for authentication, authorization, and profile tracking

#### 2. **UserDocument**
- **Purpose**: Stores identity and verification documents for users
- **Key Fields**: id, documentType, documentUrl, verificationStatus, uploadedAt
- **Relationships**: Belongs to `User`
- **Use Case**: KYC (Know Your Customer) compliance and identity verification

#### 3. **TrustHistory**
- **Purpose**: Tracks user trust score evolution over time
- **Key Fields**: id, userId, trustScore, calculatedAt, factors
- **Relationships**: Belongs to `User`
- **Use Case**: Credit risk assessment and user reputation management

#### 4. **RoleType** (Enum)
- **Purpose**: Defines user roles in the system
- **Values**: CLIENT, SELLER, IMF_AGENT, IMF_ADMIN
- **Use Case**: Role-based access control (RBAC)

#### 5. **StatusType** (Enum)
- **Purpose**: Defines user account status
- **Values**: ACTIVE, SUSPENDED, PENDING_VERIFICATION, INACTIVE
- **Use Case**: Account lifecycle management

---

## Module 2: Vehicle Module

**Purpose**: Manages vehicle listings, sales transactions, inspections, and marketplace functionality.

### Entities

#### 1. **Vehicle**
- **Purpose**: Represents vehicles listed for sale on the platform
- **Key Fields**: id, brand, model, year, price, mileage, fuelType, transmission, description, status, location
- **Relationships**: 
  - Belongs to `User` (seller)
  - Has many `VehicleImage`
  - Has many `VehicleDocument`
  - Has one `VehicleSale`
- **Use Case**: Vehicle marketplace inventory management

#### 2. **VehicleSale**
- **Purpose**: Manages the complete sales transaction between buyer and seller
- **Key Fields**: id, saleNumber, agreedPrice, status, contractUrl, createdAt, completedAt
- **Relationships**: 
  - Belongs to `Vehicle`
  - Belongs to `User` (buyer)
  - Belongs to `User` (seller)
  - Has many `SaleMilestone`
  - Has many `SaleMessage`
  - Has one `DeliveryInfo`
- **Use Case**: End-to-end sales transaction tracking

#### 3. **VehicleInspection**
- **Purpose**: Records professional vehicle inspection results
- **Key Fields**: id, inspectionDate, inspector, overallCondition, mechanicalScore, bodyScore, interiorScore, reportUrl
- **Relationships**: Belongs to `Vehicle`
- **Use Case**: Quality assurance and buyer confidence

#### 4. **VehicleImage**
- **Purpose**: Stores vehicle listing photos
- **Key Fields**: id, imageUrl, isPrimary, uploadedAt
- **Relationships**: Belongs to `Vehicle`
- **Use Case**: Visual representation in marketplace

#### 5. **VehicleDocument**
- **Purpose**: Stores vehicle legal documents (registration, insurance, etc.)
- **Key Fields**: id, documentType, documentUrl, expiryDate, verificationStatus
- **Relationships**: Belongs to `Vehicle`
- **Use Case**: Legal compliance and verification

#### 6. **VehicleFavorite**
- **Purpose**: Tracks user-saved favorite vehicles
- **Key Fields**: id, userId, vehicleId, savedAt
- **Relationships**: Links `User` and `Vehicle`
- **Use Case**: User wishlist functionality

#### 7. **UserReview**
- **Purpose**: Enables buyers to review sellers and vice versa
- **Key Fields**: id, rating, comment, reviewType, createdAt
- **Relationships**: Links `User` (reviewer) and `User` (reviewed)
- **Use Case**: Trust building and reputation system

#### 8. **SellerProfile**
- **Purpose**: Extended profile information for sellers
- **Key Fields**: id, businessName, businessType, yearsInBusiness, verificationLevel, rating
- **Relationships**: Belongs to `User`
- **Use Case**: Professional seller credibility

#### 9. **SaleMilestone**
- **Purpose**: Tracks key events in the sales process
- **Key Fields**: id, milestoneType, completedAt, notes
- **Relationships**: Belongs to `VehicleSale`
- **Use Case**: Sales progress tracking

#### 10. **SaleMessage**
- **Purpose**: Communication channel between buyer and seller
- **Key Fields**: id, senderId, message, sentAt, isRead
- **Relationships**: Belongs to `VehicleSale`
- **Use Case**: In-platform messaging

#### 11. **DeliveryInfo**
- **Purpose**: Manages vehicle delivery logistics
- **Key Fields**: id, deliveryMethod, deliveryAddress, scheduledDate, actualDate, trackingNumber, status
- **Relationships**: Belongs to `VehicleSale`
- **Use Case**: Delivery coordination

#### 12. **VehicleStatus** (Enum)
- **Purpose**: Defines vehicle listing status
- **Values**: AVAILABLE, PENDING_SALE, SOLD, REMOVED
- **Use Case**: Marketplace inventory state management

#### 13. **VehicleType** (Enum)
- **Purpose**: Categorizes vehicles
- **Values**: CAR, MOTORCYCLE, TRUCK, VAN
- **Use Case**: Vehicle classification

#### 14. **FuelType** (Enum)
- **Purpose**: Defines fuel types
- **Values**: GASOLINE, DIESEL, ELECTRIC, HYBRID
- **Use Case**: Vehicle specifications

#### 15. **TransmissionType** (Enum)
- **Purpose**: Defines transmission types
- **Values**: MANUAL, AUTOMATIC
- **Use Case**: Vehicle specifications

#### 16. **SaleStatus** (Enum)
- **Purpose**: Tracks sales transaction status
- **Values**: INITIATED, INSPECTION_PENDING, FINANCING_PENDING, PAYMENT_PENDING, DELIVERY_PENDING, COMPLETED, CANCELLED
- **Use Case**: Sales workflow management

---

## Module 3: Credit Module

**Purpose**: Manages credit applications, risk assessment, contract generation, and disbursement.

### Entities

#### 1. **CreditRequest**
- **Purpose**: Represents a user's application for vehicle financing
- **Key Fields**: id, requestedAmount, durationMonths, status, createdAt, updatedAt
- **Relationships**: 
  - Belongs to `User` (CLIENT role)
  - References `Vehicle` (vehicleId)
  - Has many `CreditDocument`
  - Has one `RiskScore`
- **Use Case**: Credit application intake and processing

#### 2. **CreditContract**
- **Purpose**: Formal loan agreement after credit approval
- **Key Fields**: id, contractNumber, amount, interestRate, durationMonths, monthlyPayment, totalToRepay, startDate, endDate, status, pdfUrl
- **Relationships**: 
  - Belongs to `CreditRequest`
  - Has many `ContractVersion`
- **Use Case**: Legal loan agreement management

#### 3. **RiskScore**
- **Purpose**: Automated credit risk assessment
- **Key Fields**: id, score, riskLevel, factors, calculatedAt, approvedBy
- **Relationships**: Belongs to `CreditRequest`
- **Use Case**: Creditworthiness evaluation

#### 4. **Disbursement**
- **Purpose**: Tracks loan fund disbursement to seller or user
- **Key Fields**: id, amount, method, status, disbursementDate, recipientAccount, transactionReference
- **Relationships**: Belongs to `CreditContract`
- **Use Case**: Fund transfer management

#### 5. **CreditDocument**
- **Purpose**: Stores credit application supporting documents
- **Key Fields**: id, documentType, documentUrl, uploadedAt, verificationStatus
- **Relationships**: Belongs to `CreditRequest`
- **Use Case**: Credit underwriting documentation

#### 6. **CreditHistory**
- **Purpose**: Records user's credit history and behavior
- **Key Fields**: id, userId, contractId, paymentBehavior, defaultCount, lastDefaultDate
- **Relationships**: Links `User` and `CreditContract`
- **Use Case**: Credit bureau reporting and future risk assessment

#### 7. **ContractVersion**
- **Purpose**: Maintains contract amendment history
- **Key Fields**: id, versionNumber, changes, createdAt, createdBy
- **Relationships**: Belongs to `CreditContract`
- **Use Case**: Contract audit trail

#### 8. **CreditRequestStatus** (Enum)
- **Purpose**: Defines credit application workflow states
- **Values**: PENDING, UNDER_REVIEW, APPROVED, REJECTED, CANCELLED
- **Use Case**: Application lifecycle management

#### 9. **CreditContractStatus** (Enum)
- **Purpose**: Defines contract lifecycle states
- **Values**: ACTIVE, COMPLETED, DEFAULTED, CANCELLED
- **Use Case**: Contract state management

#### 10. **DisbursementStatus** (Enum)
- **Purpose**: Tracks disbursement progress
- **Values**: PENDING, PROCESSING, COMPLETED, FAILED
- **Use Case**: Fund transfer tracking

#### 11. **DisbursementMethod** (Enum)
- **Purpose**: Defines disbursement channels
- **Values**: BANK_TRANSFER, MOBILE_MONEY, CASH
- **Use Case**: Payment method selection

#### 12. **RiskLevel** (Enum)
- **Purpose**: Categorizes credit risk
- **Values**: LOW, MEDIUM, HIGH, VERY_HIGH
- **Use Case**: Risk-based decision making

#### 13. **CreditDocumentType** (Enum)
- **Purpose**: Categorizes credit documents
- **Values**: INCOME_PROOF, ID_CARD, BANK_STATEMENT, EMPLOYMENT_LETTER
- **Use Case**: Document classification

---

## Module 4: Insurance Module

**Purpose**: Manages insurance products, partners, policies, quotes, and subscriptions.

### Entities

#### 1. **InsuranceProduct**
- **Purpose**: Defines insurance products offered by partners
- **Key Fields**: id, nom, description, typeVehicule, tauxBasePct, statut, conditionsPdfUrl, popular, recommended
- **Relationships**: 
  - Belongs to `InsurancePartner`
  - Has many `CoverageOption`
- **Use Case**: Insurance product catalog

#### 2. **InsurancePolicy**
- **Purpose**: Active insurance contract for a user
- **Key Fields**: id, policyNumber, primeMensuelle, primeAnnuelle, dateDebut, dateFin, statut, certificateUrl, raisonAnnulation
- **Relationships**: 
  - Belongs to `User`
  - Belongs to `InsuranceProduct`
  - Belongs to `CreditContract` (bundled insurance)
  - Belongs to `InsurancePartner`
- **Use Case**: Active insurance coverage management

#### 3. **InsurancePartner**
- **Purpose**: Third-party insurance companies integrated with platform
- **Key Fields**: id, nom, contactEmail, contactPhone, apiEndpoint, apiKey, status
- **Relationships**: Has many `InsuranceProduct`
- **Use Case**: Partner integration and management

#### 4. **Quote**
- **Purpose**: Insurance price quotation for users
- **Key Fields**: id, vehicleType, coverageType, estimatedPremium, validUntil, status
- **Relationships**: 
  - Belongs to `User`
  - Belongs to `InsuranceProduct`
- **Use Case**: Pre-purchase price estimation

#### 5. **PolicySubscription**
- **Purpose**: Tracks policy enrollment process
- **Key Fields**: id, subscriptionDate, status, paymentReference
- **Relationships**: Links `User` and `InsurancePolicy`
- **Use Case**: Policy activation workflow

#### 6. **CoverageOption**
- **Purpose**: Specific coverage features within insurance products
- **Key Fields**: id, nom, description, coverageType, maxCoverage, deductible
- **Relationships**: Belongs to `InsuranceProduct`
- **Use Case**: Granular coverage definition

#### 7. **PolicyStatus** (Enum)
- **Purpose**: Defines policy lifecycle states
- **Values**: ACTIVE, EXPIRED, CANCELLED, SUSPENDED
- **Use Case**: Policy state management

#### 8. **ProductStatus** (Enum)
- **Purpose**: Defines product availability
- **Values**: AVAILABLE, DISCONTINUED, COMING_SOON
- **Use Case**: Product catalog management

#### 9. **PartnerStatus** (Enum)
- **Purpose**: Defines partner relationship status
- **Values**: ACTIVE, INACTIVE, SUSPENDED
- **Use Case**: Partner lifecycle management

#### 10. **QuoteStatus** (Enum)
- **Purpose**: Tracks quote validity
- **Values**: PENDING, ACCEPTED, EXPIRED, REJECTED
- **Use Case**: Quote workflow management

#### 11. **SubscriptionStatus** (Enum)
- **Purpose**: Tracks subscription progress
- **Values**: PENDING, ACTIVE, CANCELLED
- **Use Case**: Subscription lifecycle

#### 12. **CoverageType** (Enum)
- **Purpose**: Categorizes insurance coverage
- **Values**: THIRD_PARTY, COMPREHENSIVE, COLLISION, THEFT
- **Use Case**: Coverage classification

---

## Module 5: Repayment Module

**Purpose**: Manages loan repayments, schedules, reminders, collections, and delinquency cases.

### Entities

#### 1. **Payment**
- **Purpose**: Records individual loan payment transactions
- **Key Fields**: id, paymentReference, amount, paymentMethod, paymentDate, transactionReference, status, processedBy, receiptUrl, notes
- **Relationships**: 
  - Belongs to `CreditContract`
  - Belongs to `User` (payer)
  - Belongs to `RepaymentSchedule`
  - Processed by `User` (agent/admin)
- **Use Case**: Payment transaction recording

#### 2. **RepaymentSchedule**
- **Purpose**: Defines expected payment installments
- **Key Fields**: id, installmentNumber, dueDate, principalAmount, interestAmount, totalAmount, status, paidDate, paidAmount
- **Relationships**: Belongs to `CreditContract`
- **Use Case**: Amortization schedule management

#### 3. **PaymentReminder**
- **Purpose**: Automated payment notifications to users
- **Key Fields**: id, reminderType, channel, scheduledDate, sentDate, message, acknowledged
- **Relationships**: Belongs to `RepaymentSchedule`
- **Use Case**: Proactive payment reminders

#### 4. **DelinquencyCase**
- **Purpose**: Manages overdue loan cases
- **Key Fields**: id, caseNumber, totalDue, principalDue, interestDue, penaltiesDue, daysOverdue, delinquencyLevel, status, lastContactDate, nextActionDate, resolutionType
- **Relationships**: Belongs to `CreditContract`
- **Use Case**: Collections and recovery management

#### 5. **CollectionTask**
- **Purpose**: Assigns collection activities to agents
- **Key Fields**: id, taskType, priority, assignedTo, dueDate, status, notes, completedAt, outcome
- **Relationships**: 
  - Belongs to `DelinquencyCase`
  - Assigned to `User` (agent)
- **Use Case**: Field agent task management

#### 6. **RecoveryAction**
- **Purpose**: Records recovery attempts and outcomes
- **Key Fields**: id, actionType, actionDate, performedBy, contactMethod, notes, result, nextSteps
- **Relationships**: Belongs to `DelinquencyCase`
- **Use Case**: Collection activity tracking

#### 7. **CollectionReceipt**
- **Purpose**: Documents cash collections by field agents
- **Key Fields**: id, receiptNumber, amount, collectedBy, collectedAt, depositedAt, depositReference
- **Relationships**: 
  - Belongs to `Payment`
  - Collected by `User` (agent)
- **Use Case**: Cash collection reconciliation

#### 8. **GracePeriod**
- **Purpose**: Defines payment grace periods and policies
- **Key Fields**: id, contractId, graceDays, extensionReason, approvedBy, startDate, endDate
- **Relationships**: Belongs to `CreditContract`
- **Use Case**: Payment flexibility management

#### 9. **PaymentTransaction**
- **Purpose**: Technical payment processing details
- **Key Fields**: id, paymentId, gatewayReference, gatewayResponse, processedAt, fees
- **Relationships**: Belongs to `Payment`
- **Use Case**: Payment gateway integration tracking

#### 10. **PaymentStatus** (Enum)
- **Purpose**: Defines payment transaction states
- **Values**: PENDING, COMPLETED, FAILED, REFUNDED
- **Use Case**: Payment lifecycle management

#### 11. **PaymentMethod** (Enum)
- **Purpose**: Defines payment channels
- **Values**: MOBILE_MONEY, BANK_TRANSFER, CASH_AGENT, CARD
- **Use Case**: Payment method selection

#### 12. **RepaymentScheduleStatus** (Enum)
- **Purpose**: Tracks installment status
- **Values**: PENDING, PAID, OVERDUE, WAIVED
- **Use Case**: Schedule item state management

#### 13. **ReminderType** (Enum)
- **Purpose**: Categorizes reminder timing
- **Values**: BEFORE_DUE, ON_DUE_DATE, AFTER_DUE
- **Use Case**: Reminder scheduling

#### 14. **ReminderChannel** (Enum)
- **Purpose**: Defines notification channels
- **Values**: SMS, EMAIL, PUSH, CALL
- **Use Case**: Multi-channel communication

#### 15. **DelinquencyLevel** (Enum)
- **Purpose**: Categorizes delinquency severity
- **Values**: EARLY (1-30 days), MODERATE (31-60 days), SERIOUS (61-90 days), CRITICAL (90+ days)
- **Use Case**: Escalation management

#### 16. **DelinquencyStatus** (Enum)
- **Purpose**: Tracks delinquency case progress
- **Values**: OPEN, IN_COLLECTION, RESOLVED, WRITTEN_OFF
- **Use Case**: Case lifecycle management

#### 17. **CollectionTaskStatus** (Enum)
- **Purpose**: Tracks task completion
- **Values**: PENDING, IN_PROGRESS, COMPLETED, CANCELLED
- **Use Case**: Task workflow management

#### 18. **CollectionTaskPriority** (Enum)
- **Purpose**: Prioritizes collection efforts
- **Values**: LOW, MEDIUM, HIGH, URGENT
- **Use Case**: Resource allocation

#### 19. **RecoveryActionType** (Enum)
- **Purpose**: Categorizes recovery activities
- **Values**: PHONE_CALL, FIELD_VISIT, LEGAL_NOTICE, NEGOTIATION
- **Use Case**: Action classification

#### 20. **RecoveryActionResult** (Enum)
- **Purpose**: Records action outcomes
- **Values**: PAYMENT_PROMISE, PARTIAL_PAYMENT, NO_CONTACT, DISPUTE
- **Use Case**: Effectiveness tracking

---

## Module 6: Claims Module

**Purpose**: Manages insurance claim filing, assessment, processing, and partner commissions.

### Entities

#### 1. **Claim**
- **Purpose**: Insurance claim filed by policyholder
- **Key Fields**: id, claimNumber, description, incidentDate, reportedDate, location, status, claimType, totalDamageAmount, approvedPayoutAmount, fraudScore
- **Relationships**: 
  - Belongs to `User`
  - Belongs to `InsurancePolicy`
  - Has many `ClaimDocument`
  - Has one `ClaimAssessment`
- **Use Case**: Claim lifecycle management

#### 2. **ClaimAssessment**
- **Purpose**: Professional claim evaluation
- **Key Fields**: id, assessorName, assessmentDate, findings, recommendedPayout, notes
- **Relationships**: Belongs to `Claim`
- **Use Case**: Claim adjudication

#### 3. **ClaimDocument**
- **Purpose**: Supporting evidence for claims
- **Key Fields**: id, documentType, documentUrl, uploadedAt
- **Relationships**: Belongs to `Claim`
- **Use Case**: Claim documentation

#### 4. **InsurancePolicy** (Reference)
- **Purpose**: Links claims to active policies
- **Key Fields**: id, policyNumber, primeMensuelle, status
- **Relationships**: Has many `Claim`
- **Use Case**: Policy-claim association

#### 5. **PremiumSchedule**
- **Purpose**: Defines insurance premium payment schedule
- **Key Fields**: id, policyId, dueDate, amount, status, paidDate
- **Relationships**: Belongs to `InsurancePolicy`
- **Use Case**: Premium collection management

#### 6. **PremiumPayment**
- **Purpose**: Records insurance premium payments
- **Key Fields**: id, scheduleId, amount, paymentDate, paymentMethod, transactionReference, status
- **Relationships**: Belongs to `PremiumSchedule`
- **Use Case**: Premium payment tracking

#### 7. **PartnerCommission**
- **Purpose**: Tracks commissions owed to insurance partners
- **Key Fields**: id, partnerId, policyId, commissionAmount, commissionRate, calculatedDate, status, paidDate
- **Relationships**: 
  - Belongs to `InsurancePartner`
  - Belongs to `InsurancePolicy`
- **Use Case**: Partner revenue sharing

#### 8. **ClaimStatus** (Enum)
- **Purpose**: Tracks claim processing progress
- **Values**: SUBMITTED, UNDER_REVIEW, APPROVED, REJECTED, PAID
- **Use Case**: Claim workflow management

#### 9. **ClaimType** (Enum)
- **Purpose**: Categorizes claim types
- **Values**: ACCIDENT, THEFT, FIRE, NATURAL_DISASTER
- **Use Case**: Claim classification

#### 10. **ClaimDocumentType** (Enum)
- **Purpose**: Categorizes claim evidence
- **Values**: POLICE_REPORT, PHOTOS, REPAIR_ESTIMATE, MEDICAL_REPORT
- **Use Case**: Document classification

#### 11. **PremiumStatus** (Enum)
- **Purpose**: Tracks premium payment status
- **Values**: PENDING, PAID, OVERDUE, WAIVED
- **Use Case**: Premium collection tracking

#### 12. **CommissionStatus** (Enum)
- **Purpose**: Tracks commission payment status
- **Values**: PENDING, CALCULATED, PAID
- **Use Case**: Partner settlement management

---

## Module 7: Security Module

**Purpose**: Provides financial monitoring, KPI tracking, alerting, and decision logging for risk management.

### Entities

#### 1. **FinancialDashboard**
- **Purpose**: Aggregates financial metrics for monitoring
- **Key Fields**: id, name, createdAt
- **Relationships**: Has many `KPI`
- **Use Case**: Executive dashboard configuration

#### 2. **KPI** (Key Performance Indicator)
- **Purpose**: Tracks specific financial metrics
- **Key Fields**: id, name, description, targetValue, currentValue, unit, status, lastCalculated
- **Relationships**: Belongs to `FinancialDashboard`
- **Use Case**: Performance monitoring

#### 3. **FinancialAlert**
- **Purpose**: Automated alerts for threshold breaches
- **Key Fields**: id, alertType, severity, message, triggeredAt, acknowledgedAt, acknowledgedBy
- **Relationships**: May reference `KPI`
- **Use Case**: Proactive risk detection

#### 4. **FinancialPeriod**
- **Purpose**: Defines accounting/reporting periods
- **Key Fields**: id, periodName, startDate, endDate, status, closedAt
- **Relationships**: None
- **Use Case**: Period-based reporting

#### 5. **DecisionRule**
- **Purpose**: Automated business rule definitions
- **Key Fields**: id, ruleName, condition, action, priority, isActive
- **Relationships**: None
- **Use Case**: Business logic automation

#### 6. **DecisionLog**
- **Purpose**: Audit trail of automated decisions
- **Key Fields**: id, ruleId, entityType, entityId, decision, executedAt
- **Relationships**: References `DecisionRule`
- **Use Case**: Decision transparency and audit

#### 7. **KPIStatus** (Enum)
- **Purpose**: Indicates KPI health
- **Values**: ON_TARGET, AT_RISK, CRITICAL
- **Use Case**: Visual status indicators

#### 8. **AlertSeverity** (Enum)
- **Purpose**: Categorizes alert urgency
- **Values**: INFO, WARNING, CRITICAL, EMERGENCY
- **Use Case**: Alert prioritization

#### 9. **PeriodStatus** (Enum)
- **Purpose**: Tracks period lifecycle
- **Values**: OPEN, CLOSED
- **Use Case**: Period management

---

## Cross-Module Relationships

### Key Integration Points

1. **User ↔ Credit**: Users apply for credit (CreditRequest)
2. **Credit ↔ Vehicle**: Credit requests reference vehicles for financing
3. **Credit ↔ Insurance**: Insurance policies bundled with credit contracts
4. **Credit ↔ Repayment**: Credit contracts generate repayment schedules
5. **Insurance ↔ Claims**: Insurance policies spawn claims
6. **Vehicle ↔ Sales**: Vehicles sold through VehicleSale transactions
7. **Repayment ↔ Collections**: Overdue payments create delinquency cases
8. **Security ↔ All Modules**: Financial monitoring tracks metrics across all modules

---

## Entity Count Summary

| Module | Total Entities | Core Entities | Enums |
|--------|---------------|---------------|-------|
| User | 5 | 3 | 2 |
| Vehicle | 16 | 12 | 4 |
| Credit | 13 | 7 | 6 |
| Insurance | 12 | 6 | 6 |
| Repayment | 20 | 9 | 11 |
| Claims | 12 | 7 | 5 |
| Security | 9 | 6 | 3 |
| **TOTAL** | **87** | **50** | **37** |

---

## Technology Stack

- **Framework**: Spring Boot
- **ORM**: Spring Data JPA / Hibernate
- **Database**: PostgreSQL (implied)
- **Architecture**: Layered (Controller → Service → Repository → Model)
- **Language**: Java
- **Build Tool**: Maven

---

*Document Generated: 2026-02-09*  
*Version: 1.0*  
*Coverage: Complete - All 7 modules, 87 entities documented*
