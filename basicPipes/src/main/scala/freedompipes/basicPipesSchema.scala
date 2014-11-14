package freedompipes

/**
 * Created by klyk on 10/26/14.
 */
object basicPipesSchema {


  //schema definition for 118DR source
  val SCHEMA_118DR_ORIG = List('DPN,'DRC_Id,'Duns,'Company,'Address1,'Address2,'Address3,'Address4,'Address5,
      'Postcode,'Telephone,'Fax,'Market_Sector_Desc,'Market_Sector_Id,'SIC_UK9203,'SIC_UK9203_Desc,'SIC_UK07,
      'SIC_UK07_Desc,'SIC_US_72,'SIC_US_72_Desc,'Date_Established,'Premises_Type,'Location_Type,
      'Companies_House_Number,'Employees_Actual,'Url,'Generic_Email,'Title1,'Forename1,'Surname1,'Job1,
      'Function1,'Title2,'Forename2,'Surname2,'Job2,'Function2,'Title3,'Forename3,'Surname3,'Job3,'Function3,
      'Title4,'Forename4,'Surname4,'Job4,'Function4,'Title5,'Forename5,'Surname5,'Job5,'Function5,'Title6,
      'Forename6,'Surname6,'Job6,'Function6,'Title7,'Forename7,'Surname7,'Job7,'Function7,'Updated,
      'Change_Add,'bnb_flag)

  //schema definition for Companies House data source
  val SCHEMA_CH_ORIG=List('CompanyName, 'CompanyNumber, 'RegAddressCareOf, 'RegAddressPOBox, 'RegAddressAddressLine1,
      'RegAddressAddressLine2, 'RegAddressPostTown, 'RegAddressCounty, 'RegAddressCountry, 'RegAddressPostCode,
      'CompanyCategory, 'CompanyStatus,'CountryOfOrigin,'DissolutionDate,'IncorporationDate,'AccountsAccountRefDay,
      'AccountsAccountRefMonth, 'AccountsNextDueDate, 'AccountsLastMadeUpDate, 'AccountsAccountCategory,
      'ReturnsNextDueDate, 'ReturnsLastMadeUpDate, 'MortgagesNumMortCharges, 'MortgagesNumMortOutstanding,
      'MortgagesNumMortPartSatisfied, 'MortgagesNumMortSatisfied, 'SICCodeSicText_1, 'SICCodeSicText_2,
      'SICCodeSicText_3, 'SICCodeSicText_4,'LimitedPartnershipsNumGenPartners,'LimitedPartnershipsNumLimPartners,
      'URI,'PreviousName_1CONDATE, 'PreviousName_1CompanyName, 'PreviousName_2CONDATE, 'PreviousName_2CompanyName,
      'PreviousName_3CONDATE, 'PreviousName_3CompanyName, 'PreviousName_4CONDATE, 'PreviousName_4CompanyName,
      'PreviousName_5CONDATE, 'PreviousName_5CompanyName, 'PreviousName_6CONDATE, 'PreviousName_6CompanyName,
      'PreviousName_7CONDATE, 'PreviousName_7CompanyName, 'PreviousName_8CONDATE, 'PreviousName_8CompanyName,
      'PreviousName_9CONDATE, 'PreviousName_9CompanyName, 'PreviousName_10CONDATE, 'PreviousName_10CompanyName
  )

  //schema definition for Trade
  val SCHEMA_TRADE = List('ET,'ACCNO,'LASTDOE,'CDUNS,'RDUNS,'DOE,'DUNS,'ACCNM,'GOVID1,'GOVID2,
      'ADDR1,'ADDR2,'ADDR3,'ADDR4,'ADDR5,'TOWN,'PCODE,'MF_CCODE,'ISO_CCODE)


  //schema definition for Royal Mail data source
  val SCHEMA_RM = List('Company,'Address1,'Address2,'Address3,'Address4,'Address5,'Postcode,'Premises_Type,'NParcels,
    'NLetters, 'Location_Type,'Companies_House_Number,'Employees_Actual,'Url,'Generic_Email,'Title1,'Forename1,
    'Surname1, 'Job1,'Function1,'Title2,'Forename2,'Surname2,'Job2,'Function2,'Title3,'Forename3,'Surname3,'Job3,
    'Function3,'Title4,'Forename4,'Surname4,'Job4,'Function4,'Title5,'Forename5,'Surname5,'Job5,'Function5,
    'Title6,'Forename6,'Surname6,'Job6,'Function6,'Title7,'Forename7,'Surname7,'Job7,'Function7,'Updated,
    'Change_Add,'bnb_flag)

  //schema for SIGNALS_DB HBase table
  val SCHEMA_SIGNALSDB = List('hashkeyAndDate,'numletters, 'numparcels, 'src118, 'trade)
}
