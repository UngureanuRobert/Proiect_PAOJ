# Aplicatie de licitatii cu functionalitati variate

# Continut:

## Models
  1. Auction - clasa de licitatii
  2. AuctionStatus - clasa ce prezinta statusul licitatiei
  3. Bid - clasa pentru oferta pe care o face un buyer in cadrul unei licitatii
  4. BidHistory - prezinta istoricul ofertelor pentru o licitatie
  5. Buyer - reprezinta capacitatea unui user de a fi cel ce ofera oferte pentru licitatie (mosteneste clasa User)
  6. Category - reprezinta categoria din care poate face parte un produs dat la licitatie
  7. Notification - un user poate primi notificari si accesa inbox-ul
  8. Payment - plata realizata de catre buyer pentru seller
  9. Product - produsul pus la licitatie
  10. Seller - reprezinta capacitatea unui user de a realiza licitatii (mosteneste clasa User)
  11. User - utilizatorul insusi
  12. Watchlist - orice buyer poate adauga anumite licitatii in Watchlist pentru a le urmari

## Service
  1. Auctionservice - Serviciul pentru licitatii, contine functionalitati diverse, precum crearea si inchiderea de licitatii, plasarea ofertelor, urmarirea evolutiei ofertelor unei licitatii,
                      verificarea valabilitatii unei licitatii, urmarirea castigurilor Seller-ilor.
  2. Notificationservice - Serviciul pentru urmarirea notificarilor utilizatorilor
  3. Productservice - Serviciul pentru adaugare si afisare de produse
  4. Userservice - Serviciu pentru useri
  5. Watchlistservice - Serviciu pentru plasarea licitatiilor in watchlist
  6. Auditservice - Serviciu pentru scrierea tuturor functionalitatilor in fisierul csv

## SQL
  Seed.sql contine tabelele specifice din baza de date: Seller, Buyer, Product, Auction, Bid

## DAO
  1. AuctionDAO
  2. BidDAO
  3. BuyerDAO
  4. ProductDAO
  5. SellerDAO
- folosite pentru a realiza modificari directe asupra bazei de date, fiecare fisier DAO este asociat tabelului corespunzator si contine toate operatiile CRUD pentru fiecare dintre tabele (create, read by id, read all, update, delete)

## Main
  Main pentru rularea aplicatiei.


# Functionalitati:
  ## Operatii specifice Users (Buyer si Seller)
  1. Register Buyer - adauga un nou buyer ce poate face oferte licitatiilor (create buyer)
  2. Register Seller - adauga un nou seller ce poate crea licitatii (create user)
  3. Get Buyer by ID - operatie de read pentru buyer in functie de ID
  4. Get Seller by ID - operatie de read pentru seller in functie de ID
  5. Delete Buyer by ID - operatie de delete buyer in functie de ID
  6. Delete Seller by ID - operatie de delete seller in functie de ID
  7. Get all buyers - operatie de read pentru toate campurile din Buyer
  8. Get all sellers - operatie de read pentru toate campurile din Seller
  9. Update Buyer by ID - operatie de update pentru un buyer in functie de id
  10. Update Seller by ID - operatie de update pentru un seller in functie de id

  ## Operatii specifice Products (produsele puse la licitatii)
  11. Add new product - adauga un nou produs ce poate mai apoi fi dat la licitatie (create product)
  12. Get Product by ID - operatie de read pentru produs in functie de id
  13. Show all products - afiseaza toate produsele existente
  14. Update Product - operatie de update pe un produs in functie de id
  15. Delete Product - operatie de delete pentru un produs in functie de id

  ## Operatii specifice Auctions (licitatii)
  16. Open auction - creeaza o noua licitatie cu atributele ei specifice (create auction)
  17. Get Auction by ID - operatie de read pentru licitatie dupa id
  18. Show all auctions - afiseaza toate licitatiile (read pentru toate licitatiile)
  19. Show auctions by status - afiseaza toate licitatiile in functie de status: Open sau Closed
  20. Update Auction - operatie de update pentru licitatie dupa id
  21. Delete Auction - operatie de delete pentru licitatie dupa id
  22. Close expired auctions - inchide definitiv licitatiile expirate
  23. Show seller's auctions - afiseaza licitatiile unui anumit seller
  24. Show seller's winnings - afiseaza tot castigul acumulat al unui anumit seller
  25. Show auctions about to expire at a certain time - afiseaza toate licitatiile ce vor expira intr-un anumit timp dat
  26. Show won auctions by buyer - afiseaza licitatiile castigate de un buyer

  ## Operatii specifice Bids (oferte pentru licitatii)
  27. Place bid - permite unui buyer sa faca o oferta (nu poate fi mai mica sau egala cu ultima oferta, operatie de create)
  28. Show biggest bid - afiseaza cea mai mare oferta pentru o anumita licitatie
  29. Show bid history - afiseaza intregul istoric pentru o anumita licitatie
  30. Get bid by id - operatie de read a unei oferte dupa id
  31. Update bid - operatie de update pe o oferta dupa id
  32. Delete bid - operatie de delete a unei oferte in functie de id

  ## Operatii pentru Watchlist
  33. Add auction to watchlist - adauga o licitatie pe watchlist-ul unui buyer
  34. Remove auction from watchlist - scoate o licitatie de pe watchlist-ul unui buyer
  35. Show all auctions in watchlist - afiseaza toate licitatiile aflate pe watchlistul unui anumit buyer

  ## Operatii pentru Notifications
  36. Send notification to user - trimite o notificare unui user
  37. Show user's notifications - afiseaza toate notificarile unui anumit user

