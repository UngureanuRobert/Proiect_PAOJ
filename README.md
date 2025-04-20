# Aplicatie de licitatii cu functionalitati variate

## Continut:

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

## Main
  Main pentru rularea aplicatiei.


# Functionalitati:

