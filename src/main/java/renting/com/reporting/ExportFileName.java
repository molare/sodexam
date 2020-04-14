/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package renting.com.reporting;


/**
 *
 * @author kamal.raimi
 */
public interface ExportFileName {
    
    public static final String EAI = "eai";
    public static final String COOPERATIVE = "cooperative";
    public static final String DEB_TYPE = "type_de_dette";
    public static final String FARM_YEAR = "annee_culture";
    public static final String IDENTITY_DOC_TYPE = "type_de_decument";
    public static final String PAYMENT_METHOD = "mode_de_paiement";
    public static final String PERIOD_TYPE = "type_de_periode";
    public static final String PLANTER_TYPE = "type_de_planteur";
    public static final String REGION = "region";
    public static final String SERVICE = "service";  
    public static final String FACTORY = "Usine";
    public static final String TRUCK = "caminon";
    public static final String TAX = "taxe";
    public static final String WEIGHT_BRIDJE = "pont_bascule";
    public static final String USER_FONCTION = "fonction_utilisateur";
    public static final String USER_PROFILS = "profile_utilisateur";
    public static final String SYNCHRONIZATION = "synchronisation";
    public static final String PRIME = "prime";
    public static final String BANK = "banque";
    public static final String PLANTER = "planteur";
    public static final String ORIGIN = "origin";
    public static final String VILLAGE = "village";
    public static final String TRUCK_DRIVER = "chauffeur";
    public static final String BANK_ACCOUNT = "compte_bancaire";
    public static final String PLANTER_SERVICE = "service_planteur";
    public static final String TANANSPORT_PRICE_ROW = "tarif_de_transport";
    public static final String FARMING = "culture";
    public static final String DEFORESTATION = "deforestation";
    public static final String PLANTING = "plantation";
    public static final String DEBT = "dette";
    public static final String PAYMENT_CALANDAR_ROW = "echeancier";
    public static final String PAY_BACK = "versement";
    public static final String SECTOR = "secteur";
    public static final String ANCADREMENT_GROUP = "groupe_encadrement";
    public static final String TRANSPORTATION_COST = "cout_de_transport";
    public static final String PURCHASE_INVOICE = "facture_achat_direct";
    public static final String PAID_BY_BANK_TRANSFER = "paiement_par_virement";
    public static final String PAID_BY_BANK_TRANSFER_AND_SECTOR = "paiement_par_virement_et_par_secteur";
    public static final String PAID_BY_CASH_REGISTER = "paiement_par_caisse";
    public static final String PAID_BY_CASH_REGISTER_AND_VILLAGE = "paiement_par_caisse_et_village";
    public static final String PAID_BY_CHECK = "paiement_par_cheque";
    public static final String BAREME_TRANSPORT = "bareme_de_transport";
    public static final String WEIGH_BRIDGE_COMPETITIVE_PRICE = "prix_pont_bascule";
    public static final String PLANTER_DELIVERY = "livraison_planteur";
    public static final String SITUATION_DEBT = "situation_dette";
    public static final String SITUATION_SERVICE = "situation_service";
    public static final String PURCHASE_TRANSPORTATION = "facture_transport";
    public static final String PURCHASE_INVOICE_MANAGER = "facture_sous_gerance";
    public static final String DELIVERY = "livraison";
    public static final String STATE_PURCHASE_INVOICE = "etat_achat_direct";
    public static final String STATE_TRANSPORTATION = "etat_des_transports";
    public static final String TRANSACTION_PLANTER = "transaction_planteur";
    public static final String WEIGHT_CONTROL = "controle_des_poids";
    public static final String PURCHASE_REGULATION = "reglement_des_achats_directs";
    public static final String PAIMENT_TYPE = "type_paiment";
    public static final String DEDUCTION_TAX = "deduction_des_taxes";
    public static final String INVOICE_MANAGEMENT = "facture_gerance";
    public static final String STATE_DIRECT_PURCHASES = "state_des_achts_directs";
    public static final String ACCOUNTING_SCHEMA = "schema_comptable";
    public static final String BULLETIN_CASH_PAID = "paiement_espece";
    public static final String BACKUP = "sauvegarde";
    public static final String PAYMENT_METHOD_STATE="Etat_mode_de_paiement_eai";
    public static final String PAYMENT_METHOD_BY_SECTOR_STATE="Etat_mode_de_paiement_eai_secteur";
    public static final String MONTHLY_STATE="releve_mensuel";
    public static final String CONTROL_OF_PAID = "controle_de_la_paie";
    public static final String TOTAL_OF_BANK_TRANSF = "comptabilisation_des_virements";
    public static final String TRANSPORTATION_REGULATION = "reglement_transport";
    public static final String PAY_BOOK = "livre_de_paie";
    public static final String SYMMARY_PURCHASE = "resume_des_achats";
    public static final String TRANSFER_ORDER = "ordre_de_virement";
    public static final String PRODUCTION_STAT = "Statistiques_production";
    public static final String PLANT_CAMPAIGN = "Campagnes_plant";
    public static final String PLANT_MATERIAL_APPLICANT = "demandeur_plant";
    public static final String PLANT_SALE_TYPE = "type_vente_plants";
    public static final String PLANT_NURSERY_SITE = "site_production";
    public static final String PLANT_DONATION = "dons_plant";
    public static final String PURCHASE_PLANT = "facture_plant";
     public static final String CREDIT_NOTE_PLANT = "facture_avoir_plant";
    public static final String UNIT_CATEGORY = "categorie_unite_mesure";
    public static final String UNIT = "unite_mesure";
    public static final String PLANT_BUDGET = "budget_pepiniere_pepiniere";
    public static final String PLANT_ANALYTICAL_ACCOUNT = "plan_analytique";
    public static final String PLANT_ANALYTICAL_ENTRY = "journal_analytique_pepiniere";
    public static final String PLANT_COST_CENTER = "centre_cout_pepiniere";
    public static final String PLANT_REMOVAL = "bon_enlevement";
    public static final String PLANT_REMOVAL_LIST = "liste_bon_enlevement";
    public static final String PLANT_SUIVI_BUDGETAIRE = "plant_suivi_budget";
    public static final String PLANT_LOT  = "plant_lot";
    public static final String PLANT_MATERIAL ="plants_materiels";
    public static final String PLANT_PAYMENT = "plants_reglements";
    public static final String PLANT_NURSERY  = "plants_pepinière";
    public static final String PLANT_DAILY_OUTPUTS  = "sorties_quotidiennes_plants";
    public static final String PLANT_WEEKLY_OUTPUTS  = "sorties_hebdo_plants";
    public static final String PLANT_SYNTHESIS_REPORTS  = "rapport_synthese_plants";
    public static final String OFFICE  = "departement_et_service";
    public static final String PLANT_TRANSPLANTING_LOT  = "Comptage_de_levée";
    public static final String PLANT_TRANSPLANTING  = "Repiquage"; 
    public static final String VALIDATION_PROFORMA =  "Validation_proforma";
    public static final String PLANT_OUT_PUT =  "Sorties_de_plants_à_valider";
    public static final String TRANSFER =  "Transfert";
    
}
