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
- folosite pentru a realiza modificari directe asupra bazei de date, fiecare fisier DAO este asociat tabelului corespunzator.

## Main
  Main pentru rularea aplicatiei.


# Functionalitati:
  1. Register Buyer - adauga un nou buyer ce poate face oferte licitatiilor
  2. Register Seller - adauga un nou seller ce poate crea licitatii
  3. Delete user - sterge contul user-ului
  4. Get all sellers - reda toti sellerii
  5. Add new product - adauga un nou produs ce poate mai apoi fi dat la licitatie
  6. Show all products - afiseaza toate produsele existente
  7. Open auction - creeaza o noua licitatie cu atributele ei specifice
  8. Show all auctions - afiseaza toate licitatiile
  9. Show auctions by status - afiseaza toate licitatiile in functie de status: Open sau Closed
  10. Place bid - permite unui buyer sa faca o oferta (nu poate fi mai mica sau egala cu ultima oferta)
  11. Show biggest bid - afiseaza cea mai mare oferta pentru o anumita licitatie
  12. Show bid history - afiseaza intregul istoric pentru o anumita licitatie
  13. Close expired auctions - inchide definitiv licitatiile expirate
  14. Show seller's auctions - afiseaza licitatiile unui anumit seller
  15. Show seller's winnings - afiseaza tot castigul acumulat al unui anumit seller
  16. Show auctions about to expire at a certain time - afiseaza toate licitatiile ce vor expira intr-un anumit timp dat
  17. Show won auctions by buyer - afiseaza licitatiile castigate de un buyer
  18. Add auction to watchlist - adauga o licitatie pe watchlist-ul unui buyer
  19. Remove auction from watchlist - scoate o licitatie de pe watchlist-ul unui buyer
  20. Show all auctions in watchlist - afiseaza toate licitatiile aflate pe watchlistul unui anumit buyer
  21. Send notification to user - trimite o notificare unui user
  22. Show user's notifications - afiseaza toate notificarile unui anumit user
