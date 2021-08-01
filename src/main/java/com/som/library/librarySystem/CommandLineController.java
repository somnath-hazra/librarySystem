package com.som.library.librarySystem;

import com.som.library.librarySystem.dto.CustomerDto;
import com.som.library.librarySystem.enums.Types;
import com.som.library.librarySystem.exception.NotFoundException;
import com.som.library.librarySystem.model.Customer;
import com.som.library.librarySystem.model.Item;
import com.som.library.librarySystem.service.CustomerService;
import com.som.library.librarySystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author :somnath
 * Comment:
 * Note this can be better coded if creation of API was allowed, With command line arg passing
 * it makes code messy and unclean.I have created RestController in the controler folder also.
 */
@Component
public class CommandLineController {

    @Autowired
    InitialDataCreation initialDataCreation;

    @Autowired
    CustomerService customerService;
    @Autowired
    ItemService itemService;

    public void displayOptions() {
        Runnable runnable = () -> {
            Scanner input = new Scanner(System.in);
            initialDataCreation.createItems();
            initialDataCreation.createDefaultCustomers();

            while(true) {
                System.out.println("Select the option below wisely: ");
                System.out.println("Register new user/customer: 1");
                System.out.println("Track a particular user/customer: 2");
                System.out.println("Get lending items for a particular user/customer: 3");
                System.out.println("Get list of overdue items a particular user/customer: 4");
                System.out.println("Check items availability: 5");
                System.out.println("Select 0 incase option is not available");

                System.out.println("Enter you selection:");
                int option = input.nextInt();

                switch (option) {
                    case 0://"Select 0 incase option is not available"
                        break;
                    case 1://"Register new user/customer: 1"
                        Customer customer = new Customer();
                        System.out.println("Enter customer name:");
                        customer.setName(input.nextLine());
                        System.out.println("Enter emailId:");
                        customer.setEmail(input.nextLine());
                        System.out.println("Enter phoneNo:");
                        customer.setPhone(input.nextLine());
                        customer.setAccountStatus(Types.AccountStatus.ACTIVE.getAccountStatus());
                        System.out.println("Created customer2 id is :" + customerService.onboardNewCustomerCustomer(customer));
                        break;
                    case 2://"Track a particular user/customer: 2"
                        System.out.println("Enter customer id:");
                        long cust_id = input.nextLong();
                        CustomerDto customerDto = customerService.getCustomerByIdWithItems(cust_id);
                        System.out.println("Customer details is :" + customerDto.toString());
                        System.out.println("Want to take an item ? Please press 1 ");
                        System.out.println("Return an item ? Please press 2 ");
                        System.out.println("Return to main menu ? Please press 3 ");
                        int optn = input.nextInt();
                            switch (optn) {
                                case 1:
                                    if(customerDto.getAccountStatus().equals(Types.AccountStatus.ACTIVE.getAccountStatus())) {
                                        System.out.println("Enter the Item Id:");
                                        long item_Id = input.nextLong();
                                        Item item = itemService.getItemById(item_Id);
                                        if(item.getAvailableNo() <= 0l)
                                            throw new NotFoundException(String.format("Item not found with ID %d", item_Id));
                                        customerService.issueItemToCustomerById(item, cust_id);
                                        item.setAvailableNo(item.getAvailableNo() - 1);
                                        itemService.saveUpdatedItem(item);
                                        System.out.println("Item is issued");
                                    } else
                                        System.out.println("Customer is not allowed to lend an item ");
                                    break;
                                case 2:
                                    System.out.println("Enter the Item Id:");
                                    long itemId = input.nextLong();
                                    Item item = itemService.getItemById(itemId);
                                    customerService.returnItemByCustomerById(item, cust_id);
                                    item.setAvailableNo(item.getAvailableNo() + 1);
                                    itemService.saveUpdatedItem(item);
                                    System.out.println("Item is returned");
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("Wrong Option selected");
                                    break;
                            }
                        break;
                    case 3://"Get lending items for a particular user/customer: 3"
                        System.out.println("Enter customer id:");
                        long cusId = input.nextLong();
                        System.out.println(customerService.getAllLendingItemByCustomerId(cusId));
                        break;
                    case 4://"Get list of overdue items a particular user/customer: 4"
                        System.out.println("Enter customer id:");
                        long custId = input.nextLong();
                        System.out.println("Overdue Item Ids are:");
                        System.out.println(customerService.getAllOverdueLendingItemByCustomerId(custId));
                        break;
                    case 5://"Check items availability:"
                        System.out.println("Enter item id:");
                        long itemId = input.nextLong();
                        System.out.println(itemService.getItemById(itemId));
                        break;
                    default:
                        System.out.println("Wrong Option selected");
                        break;

                };
            }
        };
        Thread thread = new Thread(runnable);

        // Start Threads
        thread.start();
    }
}
