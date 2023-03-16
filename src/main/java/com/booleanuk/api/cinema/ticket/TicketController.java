package com.booleanuk.api.cinema.ticket;

import com.booleanuk.api.cinema.customer.Customer;
import com.booleanuk.api.cinema.customer.CustomerRepository;
import com.booleanuk.api.cinema.response.DataResponse;
import com.booleanuk.api.cinema.response.ListResponse;
import com.booleanuk.api.cinema.screening.Screening;
import com.booleanuk.api.cinema.screening.ScreeningRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers/{customerId}/screenings")
public class TicketController {
    private final TicketRepository repository;
    private final CustomerRepository customerRepository;
    private final ScreeningRepository screeningRepository;

    public TicketController(TicketRepository repository, CustomerRepository customerRepository, ScreeningRepository screeningRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.screeningRepository = screeningRepository;
    }

    record PostTicket(Integer numSeats) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{screeningId}")
    public DataResponse<Ticket> create(@RequestBody PostTicket request, @PathVariable("customerId") Integer customerId, @PathVariable("screeningId") Integer screeningId) {
        Customer customer = this.customerRepository.findById(customerId).orElseThrow();
        Screening screening = this.screeningRepository.findById(screeningId).orElseThrow();

        Ticket ticket = new Ticket();
        ticket.setCustomer(customer);
        ticket.setScreening(screening);
        ticket.setNumSeats(request.numSeats());

        Ticket saved = this.repository.save(ticket);

        return new DataResponse<>(saved);
    }

    @GetMapping("{screeningId}")
    public ListResponse<Ticket> getAll(@PathVariable("screeningId") Integer screeningId, @PathVariable("customerId") Integer customerId) {
        return new ListResponse<>(this.repository.findByScreeningIdAndCustomerId(screeningId, customerId));
    }
}
